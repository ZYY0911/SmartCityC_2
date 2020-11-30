package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.ServiceInfo;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:06
 */

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;

    public static void newInstance(String index, Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("info", index);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        initView();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("service_info")
                .setDialog(this)
                .setJsonObject("serviceid", getIntent().getStringExtra("info"))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ServiceInfo serviceInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , ServiceInfo.class);
                        webView.loadUrl(serviceInfo.getUrl());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        webView = findViewById(R.id.web_view);
    }
}
