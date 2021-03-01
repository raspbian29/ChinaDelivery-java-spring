package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.dao.UserRepository;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserType;
import com.chinabox.delivery.request.body.NotificationBody;
import com.chinabox.delivery.service.EmailService;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/operator")
public class OperatorController {

    @Autowired
    RestControllerService restControllerService;

    @Autowired
    PackageRequestRepository packageRequestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @PostMapping(value = "findByTrackCode")
    public PackageRequest findPackageByTrackCode(@RequestBody String trackCode) {

        PackageRequest foundPackageRequest;

        if (trackCode != null) {
            foundPackageRequest = packageRequestRepository.findByTrackCode(trackCode);
        } else {
            throw new NullPointerException("Track Code is null");
        }
        if (foundPackageRequest != null && restControllerService.requestUser().getRole() == UserType.OPERATOR |
                restControllerService.requestUser().getRole() == UserType.ADMINISTRATOR) {
            if (foundPackageRequest.getChinaWarehouseArrivedDate() == null) {
                foundPackageRequest.setChinaWarehouseArrivedDate(LocalDateTime.now());
            }
            foundPackageRequest.setReceivedInChinaBy(restControllerService.requestUser().getfName() + " " +
                    restControllerService.requestUser().getlName());
            packageRequestRepository.saveAndFlush(foundPackageRequest);

        } else {
            throw new EntityNotFoundException("Package not found, or maybe you don't have permissions");
        }
        foundPackageRequest.getUser().setPassword(null);
        System.out.println(foundPackageRequest.toString());
        return foundPackageRequest;
    }


    @GetMapping(value = "findByRemoteAddress")
    public User findPackageByUserRemoteAddress(@RequestParam String remoteAddress) {
        User user = new User();
        if (remoteAddress == null && restControllerService.requestUser().getRole() != UserType.OPERATOR |
                restControllerService.requestUser().getRole() != UserType.ADMINISTRATOR
        ) {
            throw new BadRequestException("Null parameters/ no permissions");

        } else {
            if (userRepository.findByRemoteAddress(remoteAddress) != null)
                user = userRepository.findByRemoteAddress(remoteAddress);
            user.setPassword(null);
            return user;

        }
    }

    @PostMapping(value = "sendEmailNotification")
    public void sendEmailNotification(@Valid @RequestBody NotificationBody notificationBody) {
        if (restControllerService.requestUser().getRole() == UserType.ADMINISTRATOR |
                restControllerService.requestUser().getRole() == UserType.OPERATOR) {
            if (notificationBody.trackCode != null && notificationBody.id != null) {
                User user = userRepository.findById(notificationBody.id).get();
                this.emailService.sendSimpleMessage(user.getEmail(),
                        "Notificare colet", "Stimate " + user.getfName() + " " + user.getlName() + ", la data de " +
                                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                                " la depozitul din wuhan a sosit un colet nedeclarat pe numele dvs cu Track Number: " +
                                notificationBody.trackCode + ". Va rugam sa il declarati in cabinetul personal, in caz contrar va fi utilizat."
                );

            }
        }


    }




}