package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestControllerService {


    private @Autowired
    HttpServletRequest request;

    @Autowired
    @Resource
    AuthTokenRepository authTokenRepository;


    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public User requestUser() {
        AuthToken authToken = this.authTokenRepository.getByKey(request.getHeader("Authorization"));
        return authToken == null ? null : authToken.getUser();
    }


}
