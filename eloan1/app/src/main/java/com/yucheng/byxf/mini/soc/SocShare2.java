/**
 * 
 */
package com.yucheng.byxf.mini.soc;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.yucheng.apfx.soc.OnekeyShare;
import com.yucheng.apfx.soc.ShareRes;
import com.yucheng.byxf.mini.ui.R;

/**
 *
 */
public class SocShare2 {
  /**
   * 
   */
  public SocShare2(Context ctx) {
    this.ctx = ctx;
  }

  public void initShareImage() {
    try {
      SHARE_IMAGE_LFN2 = ShareRes.getCachePath(getContext(), null) + SHARE_IMAGE_SFN3;
      File file = new File(SHARE_IMAGE_LFN2);
	  file.delete();
      if (!file.exists()) {
    	  file.delete();
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        //my_ic_launcher  ic的图片！icon-75x75.pngicshare2
        BitmapFactory.decodeResource(getContext().getResources(), R.drawable.gift_icon).compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.flush();
        fos.close();
      }
    }
    catch (Throwable t) {
      t.printStackTrace();
      SHARE_IMAGE_LFN2 = null;
    }
    Log.i("SHARE_IMAGE=>>>", SHARE_IMAGE_LFN2);
  }
  
  public void initShareImage2() {
	    try {
	      SHARE_IMAGE_LFN2 = ShareRes.getCachePath(getContext(), null) + SHARE_IMAGE_SFN32;
	      File file = new File(SHARE_IMAGE_LFN2);
	      file.delete();
	      if (!file.exists()) {
	        file.createNewFile();
	        FileOutputStream fos = new FileOutputStream(file);
	        //my_ic_launcher  ic的图片！icon-75x75.png
	        BitmapFactory.decodeResource(getContext().getResources(), R.drawable.gift_icon).compress(Bitmap.CompressFormat.JPEG, 100, fos);
	        fos.flush();
	        fos.close();
	      }
	    }
	    catch (Throwable t) {
	      t.printStackTrace();
	      SHARE_IMAGE_LFN2 = null;
	    }
	    Log.i("SHARE_IMAGE=>>>", SHARE_IMAGE_LFN2);
	  }

  public void initShareText() {
    try {
      SHARE_TEXT = new HashMap<Integer, String>();
   SHARE_TEXT.put(0, getContext().getString(R.string.share_content2));
 //测试分享
  // SHARE_TEXT.put(0,"http://10.160.7.220:9080/eloan-services/v1/anon/app");
    
    }
    catch (Throwable t) {
      t.printStackTrace();
    }
    Log.i("SHARE_TEXT=>>>", SHARE_TEXT.toString());
  }

  public void showShare(boolean silent, String platform, View capView) {
    final OnekeyShare oks = new OnekeyShare();
    oks.setNotification(R.drawable.ic_launcher, getContext().getString(R.string.app_name));
    oks.setTitle(getContext().getString(R.string.evenote_title));
    oks.setTitleUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
    if (null != capView) {
      oks.setViewToShare(capView);
    }
    else {
      oks.setImagePath(SocShare2.SHARE_IMAGE_LFN2);
      oks.setImageUrl(SocShare2.SHARE_IMAGE_URL);
    }
    if (SocShare2.SHARE_TEXT != null && SocShare2.SHARE_TEXT.containsKey(0)) {
      oks.setText(SocShare2.SHARE_TEXT.get(0));
    }
    else {
    	oks.setText(getContext().getString(R.string.share_content2));
    	//测试分享
    	//oks.setText("http://10.160.7.220:9080/eloan-services/v1/anon/app");
    	
    }
    //oks.setUrl("https://etp.bobcfc.com/share");
    oks.setUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
	//测试分享
   // oks.setUrl("http://10.160.7.220:9080/eloan-services/v1/anon/app");
    oks.setFilePath(SocShare2.SHARE_IMAGE_LFN2);
    oks.setComment(getContext().getString(R.string.share));
    oks.setSite(getContext().getString(R.string.app_name));
//  oks.setSiteUrl("https://etp.bobcfc.com/");
   oks.setSiteUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
    //测试分享
    //oks.setSiteUrl("http://10.160.7.220:9080/eloan-services/v1/anon/app");
    oks.setVenueName("轻松e贷");
    oks.setVenueDescription("轻松e贷");
    oks.setLatitude(23.056081f);
    oks.setLongitude(113.385708f);
    oks.setShareFromQQAuthSupport(false);
    if (null != platform) {
      oks.setPlatform(platform);
    }
    oks.setSilent(silent);
    oks.setDialogMode();
    oks.show(getContext());
  }

  private Context getContext() {
    return ctx;
  }
//icshare2
  private static final String SHARE_IMAGE_SFN3 = "gift_icon.jpg";
  private static final String SHARE_IMAGE_SFN32 = "gift_icon.jpg";
  private static String SHARE_IMAGE_LFN2;
  private static String SHARE_IMAGE_URL;
  private static HashMap<Integer, String> SHARE_TEXT;
  private Context ctx;
}
