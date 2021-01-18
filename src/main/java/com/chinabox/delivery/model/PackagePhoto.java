package com.chinabox.delivery.model;


import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity(name = "package_photo")
public class PackagePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDateTime addedOn;
    @ManyToOne
    private PackageRequest packageRequest;
    @OneToOne
    private User operator;
    @Column
    private String imagePath; // modify
    @Column
    private byte[] picByte;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public PackageRequest getPackageRequest() {
        return packageRequest;
    }

    public void setPackageRequest(PackageRequest packageRequest) {
        this.packageRequest = packageRequest;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }


}
