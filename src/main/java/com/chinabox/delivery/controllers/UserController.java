package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.AddressRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserAddress;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/user")

public class UserController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RestControllerService restControllerService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users)
            user.setPassword(null);
        return users;
    }

    @PostMapping(value = "updateUser")
    @Modifying
    public void updateUser(@RequestBody User user) {
        User userLoggedIn = restControllerService.requestUser();
        if (user.getPassword() != null && user.getPassword().length() >= 6) {
            userLoggedIn.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getfName() != null && user.getfName().length() >= 3) {
            userLoggedIn.setfName(user.getfName());
        }
        if (user.getlName() != null && user.getlName().length() >= 3) {
            userLoggedIn.setlName(user.getlName());
        }
        if (user.getEmail() != null && user.getEmail().length() >= 8) {
            userLoggedIn.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber() != null && user.getPhoneNumber().length() >= 8) {
            userLoggedIn.setPhoneNumber(user.getPhoneNumber());
        }
        userRepository.saveAndFlush(userLoggedIn);


    }

    @PostMapping(value = "setAddress")
    public void setUserAddress(@RequestBody UserAddress address) {
        if (address != null && restControllerService.requestUser() != null) {
            UserAddress existingAddress = restControllerService.requestUser().getUserAddress();
            restControllerService.requestUser().setUserAddress(address);
            addressRepository.save(address);
            if (existingAddress != null) {
                addressRepository.deleteById(existingAddress.getId());
            }
        }


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

    @GetMapping(value = "userInfo")
    public User userInfo() {
        if (userRepository.findByEmail(restControllerService.requestUser().getEmail()) != null) {
            User foundUser = userRepository.findByEmail(restControllerService.requestUser().getEmail());
            foundUser.setPassword(null);
            return foundUser;
        }
        return null;
    }

}
