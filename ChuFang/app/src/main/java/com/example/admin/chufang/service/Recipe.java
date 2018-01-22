package com.example.admin.chufang.service;

/**
 * Created by admin on 2018/1/19.
 */

public class Recipe {
    private int imgReceipt;
    private String hostipal;
    private String doctor;
    private String effectiveDate;
    private String isEffective;

//    public Recipe(int imgReceipt, String hostipal, String doctor, String effectiveDate, String isEffective){
//        this.imgReceipt = imgReceipt;
//        this.hostipal = hostipal;
//        this.doctor = doctor;
//        this.effectiveDate = effectiveDate;
//        this.isEffective = isEffective;
//    }

    public int getImgReceipt() {
        return imgReceipt;
    }

    public void setImgReceipt(int imgReceipt) {
        this.imgReceipt = imgReceipt;
    }

    public String getHostipal() {
        return hostipal;
    }

    public void setHostipal(String hostipal) {
        this.hostipal = hostipal;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }
}
