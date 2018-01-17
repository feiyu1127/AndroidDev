package com.example.admin.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by admin on 2018/1/17.
 */

public class County extends DataSupport {
    private int id;
    private String countyName; //县的名字
    private String weatherId;
    private int cityCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}
