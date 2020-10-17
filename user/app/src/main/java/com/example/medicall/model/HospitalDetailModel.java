package com.example.medicall.model;

public class HospitalDetailModel {
    String hosName,hosCity;


    public HospitalDetailModel(){

    }

    public HospitalDetailModel(String hosName, String hosCity) {
        this.hosName = hosName;
        this.hosCity = hosCity;
    }
//    String hosLocation,hosImgUrl,hosPhone;

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getHosCity() {
        return hosCity;
    }

    public void setHosCity(String hosCity) {
        this.hosCity = hosCity;
    }

}
