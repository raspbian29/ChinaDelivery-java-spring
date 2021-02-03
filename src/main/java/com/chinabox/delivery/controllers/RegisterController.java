package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PasswordTokenRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.PasswordResetToken;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.request.body.PasswordResetBody;
import com.chinabox.delivery.service.ResetPasswordService;
import com.chinabox.delivery.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@RestController
@RequestMapping("/sign-in")
public class RegisterController {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    ResetPasswordService resetPasswordService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            this.userDetailService.registerNewUser(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception ignored) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping(value = "resetPassword")
    public ResponseEntity<?> resetPassword(HttpEntity<String> httpEntity) {
        String email = httpEntity.getBody();
        try {
            resetPasswordService.resetPassword(request, email);
        } catch (Error e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping(value = "changePassword")
    public ResponseEntity<?> changePassword(HttpEntity<PasswordResetBody> passwordResetBody) {

        String result = resetPasswordService.validatePasswordResetToken(Objects.requireNonNull(passwordResetBody.getBody()).token);

        if (result != null) {
            return ResponseEntity.badRequest().build();
        }

        PasswordResetToken foundToken = passwordTokenRepository.findByToken(Objects.requireNonNull(passwordResetBody.getBody()).token);
        if (foundToken != null) {
            User user = foundToken.getUser();
            user.setPassword(passwordEncoder.encode(passwordResetBody.getBody().password));
            userRepository.saveAndFlush(user);
            return  ResponseEntity.status(HttpStatus.OK).build();

        } else {
            return ResponseEntity.badRequest().build();

        }

    }
}


