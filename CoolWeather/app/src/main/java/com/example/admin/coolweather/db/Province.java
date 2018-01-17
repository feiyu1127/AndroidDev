package com.example.admin.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by admin on 2018/1/17.
 */

public class Province extends DataSupport { // LitePal 中的每一个实体类都必须继承 DataSupport 类
    private int id;
    private String provinceName;
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
