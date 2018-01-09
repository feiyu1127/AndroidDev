package com.example.richard.db;

/**
 * Created by Richard on 2018/1/5.
 */

public class DBUser {
    private int id;
    private String name;
    private String sex;
    private String age;

    public DBUser(){}
    public DBUser(String name,String sex,String age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public DBUser(int id,String name,String sex,String age){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
