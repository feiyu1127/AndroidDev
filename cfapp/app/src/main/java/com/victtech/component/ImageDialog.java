package com.victtech.component;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.victtech.cfapp.R;

/**
 * Created by Richard on 2018/1/19.
 */

public class ImageDialog extends Dialog implements View.OnClickListener {
    ImageView imageView;
    Context mContext;
    public ImageDialog(@NonNull Context context) {
        super(context,R.style.ImageDialogTheme);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("[][][]", "onCreate: ");
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
        this.setContentView(R.layout.image_dialog_layout);
        imageView = this.findViewById(R.id.img_dialog_avatar);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }

    public ImageDialog setImageSrc(String imgSrc){
        Glide.with(mContext).load(imgSrc).into(imageView);
        return this;
    }

    public static class Builder{
        private Context mContext;
        public Builder(Context context){
            mContext = context;
        }
        public ImageDialog create(){
            ImageDialog imageDialog = new ImageDialog(mContext);
//            imageDialog.imageView.setImageDrawable(mDrawable);
            return imageDialog;
        }
    }

}
