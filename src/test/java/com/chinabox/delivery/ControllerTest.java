package com.chinabox.delivery;

import com.chinabox.delivery.controllers.ApplicationController;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserAddress;
import com.chinabox.delivery.model.UserType;
import com.chinabox.delivery.service.MyUserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ControllerTest {

    @Autowired
    ApplicationController controller;
    MyUserDetailService service;


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void userQueryTest() {


       /* User user = new User(10L, passwordEncoder.encode("123"), "HUUUUUUUUUUUI", "Shdfsgdfgban", "+49489849", "46465f@azaza.com"
                , 4444565645655L, "Gagauzia", "Chinese Sadova", LocalDate.now(), UserType.USER);
        controller.createUser(user);

        UserAddress userAddress = new UserAddress();
        userAddress.city = "Chisinau";
        userAddress.district ="Chisinau";
        userAddress.street = "bd Cantemir ";
        userAddress.streetNumber = "4";
        userAddress.apartmentNumber = "1";
        userAddress.zipCode="MD-2001";
*/

        try {
            // System.out.println(controller.findByEmail("igaridadon@hriu.hui"));
            // System.out.println(controller.findByiDNP(44466546541595655L));
            // System.out.println(controller.findByPhoneNumber("+88888888888"));
            //System.out.println(controller.findByfName("Igariok"));
            // System.out.println(service.loadUserByUsername("igaridadon@hriu.hui"));

/*
             controller.setUserAddress(userAddress);
*/
        } catch (Exception e) {

            System.out.println("No such user");
        }
    }


}

