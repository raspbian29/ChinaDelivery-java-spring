package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.AddressRepository;
import com.chinabox.delivery.dao.AuthTokenRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/user")
public class ApplicationController {


    private @Autowired
    HttpServletRequest request;

    @Autowired
    @Resource
    UserRepository userRepository;

    @Autowired
    @Resource
    AddressRepository addressRepository;

    @Autowired
    @Resource
    AuthTokenRepository authTokenRepository;


    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public User requestUser() {
        AuthToken authToken = this.authTokenRepository.getByKey(request.getHeader("Authorization"));
        return authToken.getUser();

    }

    @PostMapping(value = "register")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users)
            user.setPassword(null);
        return users;
    }

    @PostMapping(value = "setAddress")
    public void setUserAddress(@RequestBody UserAddress address) {
        address.setUser(requestUser());
        if (addressRepository.findByUser(requestUser()) != null) {
            for (UserAddress a : addressRepository.findByUser(requestUser())) {
                addressRepository.delete(a);
            }
        }
        addressRepository.save(address);
    }


    @GetMapping(value = "findById")
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping(value = "delete")
    public void deleteUserById(Long id) {

        if (userRepository.findById(id).isPresent()) {
            userRepository.delete(userRepository.findById(id).get());
        } else {
            System.out.println("User does not exist");
        }

    }

    @GetMapping(value = "findByEmail")
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @GetMapping(value = "findByiDNP")
    public List<User> findByiDNP(Long idnp) {
        return userRepository.findByiDNP(idnp);

    }

    @GetMapping(value = "findByPhoneNumber")
    public List<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);

    }

    @GetMapping(value = "findByfName")
    public List<User> findByfName(String fName) {
        return userRepository.findByfName(fName);

    }

    @GetMapping(value = "findBylName")
    public List<User> findBylName(String lName) {
        return userRepository.findBylName(lName);

    }

}
