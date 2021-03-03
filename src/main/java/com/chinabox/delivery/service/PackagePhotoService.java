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
        PackagePhoto img = new PackagePhoto(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        img.setPackageRequest(packageRequest);
        img.setOperator(this.restControllerService.requestUser());
        img.setAddedOn(LocalDateTime.now());
        imageRepository.save(img);
    }

    public List<PackagePhoto> getImage(Long id) {
        return null;
    }

    public static byte[] compressBytes(byte[] data) {
        String encodedString = Base64.getEncoder().encodeToString(data);
        return encodedString.getBytes();
    }
}

