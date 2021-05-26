package com.akshit.bloodbankmain.Models;


public class Donor {

    private String name;
    private String phone_number;
    private String city;
    private String pin_code;
    private String blood_group;

    public Donor(String name, String phone_number, String city, String pin_code, String blood_group) {
        this.name = name;
        this.phone_number = phone_number;
        this.city = city;
        this.pin_code = pin_code;
        this.blood_group = blood_group;
    }

    public Donor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }
}