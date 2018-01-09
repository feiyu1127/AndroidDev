package com.example.richard.tools;

import com.example.richard.db.DBUser;

/**
 * Created by Richard on 2018/1/5.
 */

public interface ClickDbUserLisioner {
    public void onClickItem(DBUser user);
    public void onClickEditBtn(int id,int position);
    public void onClickRemoveBtn(int id);
}
