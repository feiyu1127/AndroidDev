package com.yucheng.byxf.mini.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;

import com.yucheng.byxf.mini.miniLoan.fragment.MiniApplyInfo;

public class SharedPreferencesUtils {

	Context context;
	String name;
	private SharedPreferences sp = null;
	public SharedPreferencesUtils(Context context, String name) {
		this.context = context;
		this.name = name;
		sp = this.context.getSharedPreferences(this.name,
				Context.MODE_PRIVATE);
	}

	
	// 保存对象到SharedPreferences里面
	public void saveObj(MiniApplyInfo info) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(baos);
			out.writeObject(info);
			String objectVal = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));
			Editor editor = sp.edit();
			editor.putString("info", objectVal);
			editor.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 从SharedPreferences读取对象
	public MiniApplyInfo readObj() {
		if (sp.contains("info")) {
			String objectVal = sp.getString("info", null);
			byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(bais);
				MiniApplyInfo info = (MiniApplyInfo) ois.readObject();
				return info;
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bais != null) {
						bais.close();
					}
					if (ois != null) {
						ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public boolean hasData(String key) {
		return this.sp.contains(key);
	}
	public void clearData() {
		this.sp.edit().clear().commit();
	}
	public void removeData(String key) {
		if (this.hasData(key))
			sp.edit().remove(key).commit();
	}
}
