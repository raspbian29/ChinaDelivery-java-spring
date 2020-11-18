package com.chinabox.delivery.request.filter;

import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    AuthTokenRepository authTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!httpServletRequest.getRequestURI().contains("rest/auth/token")) {
            String tokenKey = httpServletRequest.getHeader("Authorization");
            if (tokenKey == null) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
            } else {
                AuthToken token = this.authTokenRepository.getByKey(tokenKey);
                if (token == null || token.getCreatedOn().isAfter(token.getCreatedOn().plusDays(30))) {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
