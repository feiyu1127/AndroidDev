package com.example.admin.chufang.entity;

/**
 * Created by admin on 2018/1/24.
 */

public class Medicine {
    private int imgReceipt;
    private String hostipal;
    private String doctor;
    private String effectiveDate;
    private String isEffective;
    private String medicineCode;
    private String medicineName;
    private String medicineTrademark;
    private int medicineNum;

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

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineTrademark() {
        return medicineTrademark;
    }

    public void setMedicineTrademark(String medicineTrademark) {
        this.medicineTrademark = medicineTrademark;
    }

    public int getMedicineNum() {
        return medicineNum;
    }

    public void setMedicineNum(int medicineNum) {
        this.medicineNum = medicineNum;
    }
}
