package com.example.admin.chufang.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/1/29.
 */

public class Paginator2 {
    @SerializedName("paginator")
    public Paginator1.Pag paginator;

    public class Pag{
        public int current_page;
        public String first_page_url;
        public int from;
        public int last_page;
        public String last_page_url;
        public String next_page_url;
        public String path;
        public int per_page;

        public String prev_page_url;

        public int to;
        public int total;
    }
}
