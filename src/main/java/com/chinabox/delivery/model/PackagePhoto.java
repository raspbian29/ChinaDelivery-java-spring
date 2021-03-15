package com.chinabox.delivery.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private byte[] picByte;



    public PackagePhoto(){};

    public PackagePhoto(String name,  byte[] picByte) {
        this.name = name;
        this.picByte = picByte;
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
