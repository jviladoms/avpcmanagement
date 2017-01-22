package com.avpc.model;

/**
 * Created by Jordi on 29/10/2016.
 */
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
public class Member implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique=true,nullable=false)
    private String DNI;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname1;

    @Column(nullable = false)
    private String surname2;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = true)
    private String postalCode;

    @Column(nullable = false)
    private String mobilephoneNumber;

    @Column(nullable = true)
    private String landphoneNumber;

    @Column(nullable = false)
    private String userGroup;

    @Column(nullable = false)
    private Date registryDate = new Date();

    @Column(nullable = true)
    private Date LastAccesDate = new Date();

    @Column(nullable = true)
    private Date birthDate;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(nullable = true)
    private double longitud;

    @Column(nullable = true)
    private double lattitude;

    @Column(nullable = true)
    private Boolean disponibility;

    @Column(nullable = true)
    private Integer services;

    @Column(nullable= true)
    private String pictureUrl;

    @Column(nullable= true)
    private String tip;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilephoneNumber() {
        return mobilephoneNumber;
    }

    public void setMobilephoneNumber(String mobilephoneNumber) {
        this.mobilephoneNumber = mobilephoneNumber;
    }

    public String getLandphoneNumber() {
        return landphoneNumber;
    }

    public void setLandphoneNumber(String landphoneNumber) {
        this.landphoneNumber = landphoneNumber;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getVAT() {
        return DNI;
    }

    public void setVAT(String VAT) {
        this.DNI = VAT;
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

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    public Date getLastAccesDate() {
        return LastAccesDate;
    }

    public void setLastAccesDate(Date lastAccesDate) {
        LastAccesDate = lastAccesDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public Boolean getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Boolean disponibility) {
        this.disponibility = disponibility;
    }

    public Integer getServices() {
        return services;
    }

    public void setServices(Integer services) {
        this.services = services;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
