package com.yucheng.byxf.mini.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class MyInOutAnimation {
	
	public static void startAnimationsIn(ViewGroup viewgroup) {
		viewgroup.setVisibility(View.VISIBLE);
		for (int i = 0; i < viewgroup.getChildCount(); i++) {
			viewgroup.getChildAt(i).setVisibility(0);
			viewgroup.getChildAt(i).setClickable(true);
			viewgroup.getChildAt(i).setFocusable(true);
			viewgroup.setFocusable(true);
		}
		Animation animation =new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 

	             Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f); 
		animation.setDuration(100);
		animation.setFillAfter(true);
		viewgroup.startAnimation(animation);
	}
	
	public static void startAnimationsOut(final ViewGroup viewgroup) {
		Animation animation1 =new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 

	             Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f); 
		animation1.setDuration(100);
		animation1.setFillAfter(true);
		animation1.setStartOffset(0);
		animation1.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				viewgroup.setVisibility(View.GONE);
				viewgroup.setFocusable(false);
				for (int i = 0; i < viewgroup.getChildCount(); i++) {
					viewgroup.getChildAt(i).setVisibility(View.GONE);
					viewgroup.getChildAt(i).setClickable(false);
					viewgroup.getChildAt(i).setFocusable(false);
				}
			}
		});
		viewgroup.startAnimation(animation1);
	}
}
