package com.example.smartcityc_22.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.WdddAdapter;
import com.example.smartcityc_22.bean.Wddd;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 11:00
 */
public class WdddActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvDzf;
    private TextView tvYzf;
    private ExpandableListView expandLsit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wddd_layout);
        initView();
        title.setText("我的订单");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVoley();
        expandLsit.setGroupIndicator(null);
        tvDzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate("N");
                tvDzf.setBackgroundResource(R.drawable.text_line);
                tvDzf.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvYzf.setTextColor(Color.parseColor("#333333"));
                tvYzf.setBackgroundResource(R.drawable.text_no_line);
            }
        });
        tvYzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate("Y");
                tvYzf.setBackgroundResource(R.drawable.text_line);
                tvYzf.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvDzf.setTextColor(Color.parseColor("#333333"));
                tvDzf.setBackgroundResource(R.drawable.text_no_line);
            }
        });
    }


    List<Wddd> wddds, wdddsList;

    private void setVoley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderBus")
                .setJsonObject("name", "abc")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wddds = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<Wddd>>() {
                                }.getType());

                        setDate("N");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setDate(String n) {
        wdddsList = new ArrayList<>();
        for (int i = 0; i < wddds.size(); i++) {
            Wddd wddd = wddds.get(i);
            if (wddd.getIsPay().equals(n)) {
                wdddsList.add(wddd);
            }
        }
        expandLsit.setAdapter(new WdddAdapter(wdddsList));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvDzf = findViewById(R.id.tv_dzf);
        tvYzf = findViewById(R.id.tv_yzf);
        expandLsit = findViewById(R.id.expand_lsit);
    }
}
