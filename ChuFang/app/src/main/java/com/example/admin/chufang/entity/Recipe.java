package com.example.admin.chufang.entity;

import android.graphics.Bitmap;

/**
 * Created by admin on 2018/1/19.
 */

public class Recipe {
    private String imgReceipt;
    private String hospital;
    private String doctor;
    private String effectiveDate;
    private String isEffective;

    public String getImgReceipt() {
        return imgReceipt;
    }

    public void setImgReceipt(String imgReceipt) {
        this.imgReceipt = imgReceipt;
    }

    public String getHostipal() {
        return hospital;
    }

    public void setHostipal(String hostipal) {
        this.hospital = hostipal;
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
