package com.example.admin.listviewtest;

/**
 * Created by admin on 2018/1/8.
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return  name;
    }

    public int getImageId(){
        return  imageId;
    }

}
