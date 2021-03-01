package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackagePhoto;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.model.UserType;
import com.chinabox.delivery.service.PackagePhotoService;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping(value = "uploadPackagePhoto")
    public ResponseEntity<Void> uploadPhoto(@RequestParam("imageFile") MultipartFile file,
                                            @RequestParam("packageRequestId") Long id) throws IOException {
        UserType userRole = restControllerService.requestUser().getRole();
        Optional<PackageRequest> packageRequest = this.packageRequestRepository.findById(id);
        boolean isUserAllowed = userRole == UserType.ADMINISTRATOR || userRole == UserType.OPERATOR;
        if (packageRequest.isPresent() && isUserAllowed) {
            packagePhotoService.uploadImage(file, packageRequest.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping(value = "getPackagePhotos")
    public List<PackagePhoto> getImage(@RequestParam("packageRequestId") Long id) {

        List<PackagePhoto> foundPhotos = packagePhotoService.getImage(id);
        if (foundPhotos != null) {
            return foundPhotos;
        }
        return null;

    }

}
