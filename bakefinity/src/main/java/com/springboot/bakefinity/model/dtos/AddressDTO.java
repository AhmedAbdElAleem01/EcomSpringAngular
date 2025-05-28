package com.springboot.bakefinity.model.dtos;

public class AddressDTO {
    private int id;
    private int userId;
    private int buildingNo;
    private String street;
    private String city;
    private String country;

    public AddressDTO() {
    }

    public AddressDTO(int userId, int buildingNo, String street, String city, String country) {
        this.userId = userId;
        this.buildingNo = buildingNo;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", buildingNo=" + buildingNo +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
