package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.dao.PasswordTokenRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.model.PasswordResetToken;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.Random;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthTokenRepository tokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("Not found!");
        return new MyUserPrincipal(user);
    }

    public UserDetails getByTokenKey(String tokenKey) {
        AuthToken token = tokenRepository.getByKey(tokenKey);
        return token != null
                ? new MyUserPrincipal(token.getUser())
                : null;
    }

    public User registerNewUser(User newUser) throws EntityExistsException {
        if (this.userRepository.findByEmail(newUser.getEmail()) != null)
            throw new EntityExistsException("User already exist!");
        newUser.setRemoteAddress(this.generateChinaId());
        newUser.setCreatedDate(LocalDate.now());
        newUser.setRole(UserType.USER);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        this.userRepository.save(newUser);
        emailService.sendSimpleMessage(newUser.getEmail(), "test", "Welcome " + newUser.getfName());
        return newUser;
    }

    private String generateChinaId() {
        String chinaId = ("MD-" + String.format("%06d", new Random().nextInt(999999)));
        while (userRepository.findByRemoteAddress(chinaId) != null) {
            chinaId = ("MD-" + String.format("%06d", new Random().nextInt(999999)));
        }
        return chinaId;
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }
}
