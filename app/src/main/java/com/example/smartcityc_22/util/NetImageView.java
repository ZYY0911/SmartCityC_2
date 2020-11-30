package com.example.smartcityc_22.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.net.VolleyImage;
import com.example.smartcityc_22.net.VolleyLoImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:46
 */
public class NetImageView extends androidx.appcompat.widget.AppCompatImageView {
    public NetImageView(@NonNull Context context) {
        super(context);
    }

    public NetImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NetImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setMyUrl(String path) {
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(path)
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
}
