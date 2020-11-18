package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthTokenRepository tokenRepository;

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
}
