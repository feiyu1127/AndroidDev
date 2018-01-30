package com.example.admin.chufang.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2018/1/29.
 */

public class InnerData {
        public int id;
        public String hospital;
        public String doctor;
        public int status;
        public String recipe_img;
        public int user_id;
        public int shop_id;
        public String created_at;
        public String updated_at;
        public int recipe_id;
        public int index;

        @SerializedName("medicines")
        public List<Medicine> medicineList ;

}
