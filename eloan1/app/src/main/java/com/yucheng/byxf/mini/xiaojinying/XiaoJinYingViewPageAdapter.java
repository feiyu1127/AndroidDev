package com.yucheng.byxf.mini.xiaojinying;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class XiaoJinYingViewPageAdapter extends PagerAdapter {
	
	 private ArrayList<View> listViews;// content  
     private int size;// 页数  
     public XiaoJinYingViewPageAdapter(ArrayList<View> listViews, int size) {
		super();
		this.listViews = listViews;
		this.size = size;
	}
	public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据  这个可是重点啊  
         this.listViews = listViews;  
         size = listViews == null ? 0 : listViews.size();  
     }  
     @Override  
     public int getCount() {// 返回数量  
         return size;  
     }  
     @Override  
     public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象  
         ( (ViewGroup) arg0).removeView(listViews.get(arg1 % size));  
     }  

     @Override  
     public void finishUpdate(View arg0) {  
     }  

     @Override  
     public Object instantiateItem(View arg0, int arg1) {// 返回view对象  
         try {  
             ((ViewGroup) arg0).addView(listViews.get(arg1 % size), 0);  
         } catch (Exception e) {  
             Log.e("zhou", "exception：" + e.getMessage());  
         }  
         return listViews.get(arg1 % size);  
     }  

     @Override  
     public boolean isViewFromObject(View arg0, Object arg1) {  
         return arg0 == arg1;  
     }  

 }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	private List<View> mViewpage;
//
//	public XiaoJinYingViewPageAdapter(List<View> mViewpage) {
//		this.mViewpage = mViewpage;
//	}
//
//	@Override
//	public void destroyItem(ViewGroup container, int position, Object object) {
//		container.removeView(mViewpage.get(position));// 删除页卡
//	}
//
//	@Override
//	public Object instantiateItem(View container, int position) {// 这个方法用来实例化页卡
//		// TODO Auto-generated method stub
//		((ViewGroup) container).addView(mViewpage.get(position), 0);// 添加页卡
//		return mViewpage.get(position);
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return mViewpage.size();
//	}
//
//	@Override
//	public boolean isViewFromObject(View arg0, Object arg1) {
//		// TODO Auto-generated method stub
//		return arg0 == arg1;
//	}
//}
