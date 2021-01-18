package com.chinabox.delivery;

import com.chinabox.delivery.service.RestControllerService;
import com.chinabox.delivery.controllers.PackageRequestController;
import com.chinabox.delivery.service.UserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ControllerTest {

    @Autowired
    RestControllerService userController;
    @Autowired
    PackageRequestController packageController;

    UserDetailService service;


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void userQueryTest() {


        /*User user = new User(11L, passwordEncoder.encode("123"), "Test", "MrTest", "+373335", "mrtest@gmail.com"
                , 2004036045675L, "Chisinau", "MD-55489", LocalDate.now(), UserType.USER);
        userController.createUser(user);
*/
        /*UserAddress userAddress = new UserAddress();
        userAddress.city = "Chisinau";
        userAddress.district ="Chisinau";
        userAddress.street = "bd Cantemir ";
        userAddress.streetNumber = "4";
        userAddress.apartmentNumber = "1";
        userAddress.zipCode="MD-2001";
*/

        /*PackageRequest packageRequest = new PackageRequest();
        packageRequest.setUser(userController.findByEmail("mrtest@gmail.com"));
        packageRequest.setCreatedDate(LocalDateTime.now());
        packageRequest.setAmount(55.0);
        packageRequest.setCurrency(Currency.EUR);
        packageRequest.setItemType(ItemType.SPARE_PARTS);
        packageRequest.setShopName("Taobao");
        packageRequest.setItemInsurance(true);
        packageRequest.setItemCheck(true);

        try {
            packageController.addPackageRequest(packageRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such user");
        }
    }*/


    }
}

