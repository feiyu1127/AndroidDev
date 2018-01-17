package com.example.admin.coolweather.util;

import android.text.TextUtils;

import com.example.admin.coolweather.db.City;
import com.example.admin.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2018/1/17.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     * @param response
     * @return
     * @throws JSONException
     */
    public static boolean handleProvinceResponse(String response) throws JSONException {
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i < allProvince.length();i++){
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return true;
        }
        return  false;
    }


    /**
     * 处理县级数据
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for(int i = 0; i < allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
