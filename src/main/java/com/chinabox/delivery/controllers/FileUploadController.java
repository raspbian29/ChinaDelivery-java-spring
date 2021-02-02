package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackagePhoto;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.model.UserType;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")

@Controller
@RequestMapping("rest/upload")
public class FileUploadController {

    @Autowired
    RestControllerService restControllerService;
    @Autowired
    PackageRequestRepository packageRequestRepository;

    @PostMapping(value = "photo")
    public void uploadPhoto(@RequestParam("imageFile") MultipartFile file, PackageRequest packageRequest)  {
        if (packageRequestRepository.findById(packageRequest.getId()).isPresent()) {
            if (restControllerService.requestUser().getRole() == UserType.OPERATOR |
                    restControllerService.requestUser().getRole() == UserType.ADMINISTRATOR) {
                PackagePhoto photo = new PackagePhoto();
                photo.setName(packageRequest.getTrackCode() + " " + file.getName());
                photo.setAddedOn(LocalDateTime.now());
                photo.setOperator(restControllerService.requestUser());
                photo.setPackageRequest(packageRequest);
            }
        }
    }


}
