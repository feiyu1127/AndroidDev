package com.example.admin.uibestpractice;

/**
 * Created by admin on 2018/1/9.
 */

public class Msg {
    public static final int TYPE_RECEIVED = 0; //收到消息
    public static final int TYPE_SENT = 1; //发送消息
    private String content;
    private int type;

    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }
}
