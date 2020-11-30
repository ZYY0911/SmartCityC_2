package com.example.smartcityc_22.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.net.VolleyImage;
import com.example.smartcityc_22.net.VolleyLoImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:39
 */
public class RangeImage extends androidx.appcompat.widget.AppCompatImageView {

    public RangeImage(@NonNull Context context) {
        super(context);
    }

    public RangeImage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeImage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int width, height;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
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

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, width, height), 20, 20, Path.Direction.CCW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
