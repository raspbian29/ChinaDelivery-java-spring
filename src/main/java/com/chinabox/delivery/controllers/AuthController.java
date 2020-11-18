package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.request.body.LoginRequest;
import com.chinabox.delivery.service.AuthService;
import com.chinabox.delivery.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/rest/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/token")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String tokenKey = jwtUtils.generateJwt(auth);
        AuthToken token = authService.getOrCreateAuthToken(tokenKey, userRepository.findByEmail(request.email));
        return new ResponseEntity<HashMap<String, String>>(new HashMap<>() {{
            put("key", token.getKey());
            put("created_on", token.getCreatedOn().toString());
        }}, HttpStatus.OK);
    }
}
