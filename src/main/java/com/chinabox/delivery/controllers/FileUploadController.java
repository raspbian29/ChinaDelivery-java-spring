package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackagePhoto;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserType;
import com.chinabox.delivery.request.body.ImageUpload;
import com.chinabox.delivery.service.PackagePhotoService;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/upload")
public class FileUploadController {

    @Autowired
    RestControllerService restControllerService;
    @Autowired
    PackageRequestRepository packageRequestRepository;
    @Autowired
    PackagePhotoService packagePhotoService;


    @PostMapping(value = "uploadPackagePhoto", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> uploadPhoto(@RequestParam("imageFile") MultipartFile[] photos,
                                            @RequestParam("packageRequestId") Long id) throws IOException {
        Optional<PackageRequest> packageRequest = this.packageRequestRepository.findById(id);
        UserType userRole = restControllerService.requestUser().getRole();
        boolean isUserAllowed = userRole == UserType.ADMINISTRATOR || userRole == UserType.OPERATOR;
        if (!(packageRequest.isPresent() && isUserAllowed)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for (MultipartFile photo : photos) {
            packagePhotoService.uploadImage(photo, packageRequest.get());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "uploadBase64")
    public ResponseEntity<Void> uploadPhoto2(@RequestBody() ImageUpload body) {
        List<String> base64images = body.getImages();
        Long packageRequestId = body.getPackageRequestId();
        Optional<PackageRequest> packageRequest = this.packageRequestRepository.findById(packageRequestId);
        UserType userRole = restControllerService.requestUser().getRole();
        boolean isUserAllowed = userRole == UserType.ADMINISTRATOR || userRole == UserType.OPERATOR;
        if (!(packageRequest.isPresent() && isUserAllowed)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for (String base64img : base64images) {
            packagePhotoService.uploadBase64(base64img, packageRequest.get());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping(value = "getPackagePhotos")
    public ResponseEntity<List<PackagePhoto>> getImage(@RequestParam("packageRequestId") Long id) {
        Optional<PackageRequest> pro = packageRequestRepository.findById(id);
        if (pro.isEmpty()) {
            System.out.println("Package not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        PackageRequest pr = pro.get();
        User requestUser = restControllerService.requestUser();
        User prUser = pr.getUser();
        UserType userRole = requestUser.getRole();
        boolean isUserAllowed = (userRole == UserType.ADMINISTRATOR || userRole == UserType.OPERATOR);
        if (!isUserAllowed || !prUser.getId().equals(requestUser.getId())) {
            System.out.println("-User has no rights or not allowed " + requestUser);
            System.out.println("is user allowed " + isUserAllowed);
            System.out.println("is user's pachage? " + prUser.getId().equals(requestUser.getId()));
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        System.out.println(requestUser);
        return ResponseEntity.status(HttpStatus.OK).body(packagePhotoService.getImages(id));
    }

}
