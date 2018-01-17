package com.example.admin.materialtest;

/**
 * Created by admin on 2018/1/17.
 */

public class Fruit {
    private String name;
    private int imageId; //图片对应的资源ID

    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }


    public int getImageId() {
        return imageId;
    }


}
