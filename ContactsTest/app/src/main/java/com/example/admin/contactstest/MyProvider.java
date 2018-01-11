package com.example.admin.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2018/1/11.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMacther;

    static {
        uriMacther = new UriMatcher(UriMatcher.NO_MATCH);
        uriMacther.addURI("com.example.admin.contactstest.provide","table1",TABLE1_DIR);
        uriMacther.addURI("com.example.admin.contactstest.provide","table1/#",TABLE1_ITEM);
        uriMacther.addURI("com.example.admin.contactstest.provide","table2",TABLE2_DIR);
        uriMacther.addURI("com.example.admin.contactstest.provide","table2/#",TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMacther.match(uri)){
            case TABLE1_DIR:
                //查询 table1 表中所有的数据
                break;
            case TABLE1_ITEM:
                //查询 table1 表中的单条数据
                break;
            case TABLE2_DIR:
                //查询 table1 表中所有的数据
                break;
            case TABLE2_ITEM:
                //查询 table2 表中的单条数据
                break;
            default:
                break;
        }

        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
