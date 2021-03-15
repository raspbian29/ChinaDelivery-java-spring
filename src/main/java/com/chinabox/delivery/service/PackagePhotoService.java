package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.PackagePhotoRepository;
import com.chinabox.delivery.model.PackagePhoto;
import com.chinabox.delivery.model.PackageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;


@Controller
public class PackagePhotoService {
    @Autowired
    PackagePhotoRepository imageRepository;
    @Autowired
    RestControllerService restControllerService;


    public void uploadImage(MultipartFile file, PackageRequest packageRequest) throws IOException {

        byte[] image = Base64.getEncoder().encode(file.getBytes());
        PackagePhoto img = new PackagePhoto(file.getOriginalFilename(),image);
        img.setPackageRequest(packageRequest);
        img.setOperator(this.restControllerService.requestUser());
        img.setAddedOn(LocalDateTime.now());
        imageRepository.save(img);
    }

    public void uploadBase64(String base64Img, PackageRequest packageRequest) {

        byte[] image = base64Img.getBytes();
        PackagePhoto img = new PackagePhoto(packageRequest.getTrackCode(),image);
        img.setPackageRequest(packageRequest);
        img.setOperator(this.restControllerService.requestUser());
        img.setAddedOn(LocalDateTime.now());
        imageRepository.save(img);
    }

    public List<PackagePhoto> getImages(Long id) {
        List<PackagePhoto> packagePhotos = imageRepository.findByPackageRequestId(id);
        for (PackagePhoto photo: packagePhotos) {
            photo.getOperator().setPassword(null);
            photo.getPackageRequest().getUser().setPassword(null);
        }
        return packagePhotos;
    }

}

