package com.example.richard.tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/4.
 */

public class ActivityContent {
    private static List<Activity> activityList = new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activityList
             ) {
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
