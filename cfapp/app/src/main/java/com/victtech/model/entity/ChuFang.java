package com.victtech.model.entity;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFang {
    private int id;
    private String name;
    private String hospital;
    private String avatar;
    private String recipe_img;
    private String created_at;
    private Medicine[] medicines;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRecipe_img() {
        return recipe_img;
    }

    public void setRecipe_img(String recipe_img) {
        this.recipe_img = recipe_img;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Medicine[] getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicine[] medicines) {
        this.medicines = medicines;
    }
}
