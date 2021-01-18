package com.chinabox.delivery.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String password;
    @Column
    private String fName;
    @Column
    private String lName;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private Long iDNP;
    @Column
    private String localAddress;
    @Column
    private String remoteAddress;
    @Column
    private LocalDate createdDate;
    @Column
    private UserType role;


    public User() {

    }
    public User(Long id, String password, String fName, String lName, String phoneNumber, String email,
                Long iDNP, String localAddress, String remoteAddress, LocalDate createdDate, UserType role) {
        this.id = id;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.iDNP = iDNP;
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
        this.createdDate = createdDate;
        this.role = role;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getfName() { return fName; }

    public void setfName(String fName) { this.fName = fName; }

    public String getlName() { return lName; }

    public void setlName(String lName) { this.lName = lName; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Long getIDNP() { return iDNP; }

    public void setIDNP(Long iDNP) { this.iDNP = iDNP; }

    public String getLocalAddress() { return localAddress; }

    public void setLocalAddress(String localAdress) { this.localAddress = localAdress; }

    public String getRemoteAddress() { return remoteAddress; }

    public void setRemoteAddress(String remoteAdress) { this.remoteAddress = remoteAdress; }

    public LocalDate getCreatedDate() { return createdDate; }

    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public UserType getRole() { return role; }

    public void setRole(UserType role) { this.role = role; }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", IDNP=" + iDNP +
                ", localAddress='" + localAddress + '\'' +
                ", remoteAddress=" + remoteAddress +
                ", createdDate=" + createdDate +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fName, user.fName) &&
                Objects.equals(lName, user.lName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(iDNP, user.iDNP) &&
                Objects.equals(localAddress, user.localAddress) &&
                Objects.equals(remoteAddress, user.remoteAddress) &&
                Objects.equals(createdDate, user.createdDate) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, fName, lName, email, iDNP, localAddress, remoteAddress, createdDate, role);
    }
}



