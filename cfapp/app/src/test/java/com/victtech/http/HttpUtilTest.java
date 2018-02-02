package com.victtech.http;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Richard on 2018/1/25.
 */
public class HttpUtilTest {
    @Test
    public void getRequest(){

        HttpUtil.getRequest("recipes?status=5&page=1", new HttpCallBackLisioner() {
            @Override
            public void onFinish(String requestString) {
                System.out.println(requestString);
                assertEquals(true,true);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                assertEquals(true,false);
            }
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}