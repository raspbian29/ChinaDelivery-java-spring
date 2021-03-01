package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.PackagePhotoRepository;
import com.chinabox.delivery.model.PackagePhoto;
import com.chinabox.delivery.model.PackageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Controller
public class PackagePhotoService {


    @Autowired
    PackagePhotoRepository imageRepository;
    @Autowired
    RestControllerService restControllerService;


    public void uploadImage(MultipartFile file, PackageRequest packageRequest) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        PackagePhoto img = new PackagePhoto(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        img.setPackageRequest(packageRequest);
        img.setOperator(this.restControllerService.requestUser());
        img.setAddedOn(LocalDateTime.now());
        imageRepository.save(img);
    }


    public List<PackagePhoto> getImage(Long id)  {

        List<PackagePhoto> retrievedImages = imageRepository.findByPackageRequestId(id);

        for (PackagePhoto photo: retrievedImages) {
            photo.setPicByte(decompressBytes(photo.getPicByte()));;
        }
        return retrievedImages;
    }


    //  compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();

        } catch (IOException e) {

        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();

    }


    // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }

            outputStream.close();

        } catch (IOException ioe) {

        } catch (DataFormatException e) {

        }
        return outputStream.toByteArray();

    }

}

