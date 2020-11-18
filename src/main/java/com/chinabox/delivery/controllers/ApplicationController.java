package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/user")
public class ApplicationController {
    @Autowired
    @Resource
    UserRepository userRepository;

    @PostMapping(value = "register")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }


    @GetMapping(value = "list")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users)
            user.setPassword(null);
        return users;
    }


    @GetMapping(value = "findById")
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @DeleteMapping(value = "delete")
    public void deleteUserById(Long id) {
        userRepository.delete(userRepository.findById(id).get());

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
