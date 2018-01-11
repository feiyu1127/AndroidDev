package com.example.admin.mywebview;

/**
 * Created by admin on 2018/1/11.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public static int getTypeReceived() {
        return TYPE_RECEIVED;
    }

    public static int getTypeSend() {
        return TYPE_SEND;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
