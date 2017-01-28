package com.avpc.model;

/**
 * Created by Jordi on 29/10/2016.
 */
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import javax.persistence.*;


@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "memberId")
    private Long id;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname1;

    @Column(nullable = true)
    private String surname2;

    @Column(nullable = false)
    private String mobilePhoneNumber;

    @Column(nullable = true)
    private String landPhoneNumber;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = true)
    private String postalCode;

    @Column(nullable = false)
    private String userGroup;

    @Column(nullable = false)
    private Date registryDate = new Date();

    @Column(nullable = true)
    private Date deletionDate = new Date();

    @Column(nullable = true)
    private Date lastAccesDate = new Date();

    @Column(nullable = true)
    private Date birthDate;

    @Column(nullable = true)
    private double longitude;

    @Column(nullable = true)
    private double latitude;

    @Column(nullable = true)
    private Boolean availability;

    @Column(nullable= true)
    private String photoURL;

    @Column(nullable = true)
    private Integer services;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getLandPhoneNumber() {
        return landPhoneNumber;
    }

    public void setLandPhoneNumber(String landPhoneNumber) {
        this.landPhoneNumber = landPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public Date getLastAccesDate() {
        return lastAccesDate;
    }

    public void setLastAccesDate(Date lastAccesDate) {
        this.lastAccesDate = lastAccesDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Integer getServices() {
        return services;
    }

    public void setServices(Integer services) {
        this.services = services;
    }
}
