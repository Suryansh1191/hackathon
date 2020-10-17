package com.example.medicall.model;

public class DoctorModel {
    String DocName,DocSpecial;

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getDocSpecial() {
        return DocSpecial;
    }

    public void setDocSpecial(String docSpecial) {
        DocSpecial = docSpecial;
    }

    public DoctorModel(String docName, String docSpecial) {
        DocName = docName;
        DocSpecial = docSpecial;
    }
}
