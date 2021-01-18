package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.PasswordTokenRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.PasswordResetToken;
import com.chinabox.delivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.UUID;


@Service
public class ResetPasswordService {

    @Autowired
    Environment env;
    @Autowired
    MessageSource messages;
    @Autowired
    private EmailService emailService;
    @Autowired
    PasswordTokenRepository passwordTokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestControllerService restControllerService;



    public ResetPasswordService() {
    }


    public void resetPassword(HttpServletRequest request, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user, token);
        emailService.sendSimpleMessage(constructResetTokenEmail(request.getHeader("Origin") , token, user));

    }



    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    private SimpleMailMessage constructResetTokenEmail(String contextPath, String token, User user) {
        String url = contextPath + "/change-password?token=" + token;
        return constructEmail("Reset Password", " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("support.email");
        return email;
    }

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
