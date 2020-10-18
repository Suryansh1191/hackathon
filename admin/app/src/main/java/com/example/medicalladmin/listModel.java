package com.example.medicalladmin;

public class listModel {
    String name,age,address,dob,doctor,date,phone,sex,symptoms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public listModel(){

    }

    public listModel(String name, String age, String address, String dob, String doctor, String date, String phone, String sex, String symptoms) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.dob = dob;
        this.doctor = doctor;
        this.date = date;
        this.phone = phone;
        this.sex = sex;
        this.symptoms = symptoms;
    }

}
