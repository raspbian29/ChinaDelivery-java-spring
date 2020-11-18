package com.chinabox.delivery.utils;

import com.chinabox.delivery.service.MyUserPrincipal;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    public String generateJwt(Authentication auth) {
        UserDetails user = (MyUserPrincipal) auth.getPrincipal();
        return "Bearer " + Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer("China Box")
                .setIssuedAt(new Date())
                .compact();
    }
}
