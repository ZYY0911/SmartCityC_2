package com.example.smartcityc_22.net;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.smartcityc_22.AppClient;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:42
 */
public class VolleyImage extends Thread {
    private String url;
    private VolleyLoImage volleyLoImage;

    public VolleyImage setVolleyLoImage(VolleyLoImage volleyLoImage) {
        this.volleyLoImage = volleyLoImage;
        return this;
    }

    public VolleyImage setUrl(String url) {
        this.url = url;
        Log.i("aaa", "setUrl: "+url);
        return this;
    }

    @Override
    public void run() {
        super.run();
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                volleyLoImage.onResponse(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyLoImage.onErrorResponse(volleyError);

            }
        });
        AppClient.add(request);
    }
}
