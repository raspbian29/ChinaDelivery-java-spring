package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    AuthTokenRepository authTokenRepository;

    public AuthToken getOrCreateAuthToken(String key, User user) {
        List<AuthToken> tokens = authTokenRepository.getByUser(user);
        authTokenRepository.deleteAll(tokens);
        AuthToken token = new AuthToken();
        token.setKey(key);
        token.setUser(user);
        token.setCreatedOn(LocalDateTime.now());
        authTokenRepository.save(token);
        return token;
    }
}
