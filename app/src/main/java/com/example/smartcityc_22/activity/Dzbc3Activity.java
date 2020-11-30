package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.BusStation;
import com.example.smartcityc_22.bean.BusTitle;
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
 * @Create by 张瀛煜 on 2020/10/29 at 10:44
 */
public class Dzbc3Activity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private EditText etName;
    private EditText etTel;
    private Spinner etUp;
    private Spinner etDown;
    private TextView tvNext;

    public static void newInstance(BusTitle busTitle, String time, Context context) {
        Intent intent = new Intent(context, Dzbc3Activity.class);
        intent.putExtra("info", busTitle);
        intent.putExtra("time", time);
        context.startActivity(intent);
    }

    private BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout3);
        initView();
        AppClient.add(this);
        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLine.setText("起点：" + busTitle.getStartSite() + "   终点：" + busTitle.getEndSite());
        setLocation();
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dzbc4Activity.newInstance(busTitle
                        , getIntent().getStringExtra("time")
                        , etName.getText().toString()
                        , etTel.getText().toString()
                        , etUp.getSelectedItem().toString()
                        , etDown.getSelectedItem().toString(), Dzbc3Activity.this);
            }
        });
    }

    List<BusStation> busStations;

    List<String> strings, strings1;

    private void setLocation() {
        strings = new ArrayList<>();
        strings1 = new ArrayList<>();
        VolleyTo volleyTo1 = new VolleyTo();
        volleyTo1.setUrl("busStationById")
                .setJsonObject("busid", busTitle.getBusid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busStations = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<BusStation>>() {
                                }.getType());
                        for (int i = 0; i < busStations.size(); i++) {
                            BusStation busStation = busStations.get(i);
                            strings.add(busStation.getSiteName());
                            strings1.add(busStation.getSiteName());
                        }
                        etUp.setAdapter(new ArrayAdapter<String>(Dzbc3Activity.this, android.R.layout.simple_list_item_1
                                , strings));
                        etDown.setAdapter(new ArrayAdapter<String>(Dzbc3Activity.this, android.R.layout.simple_list_item_1
                                , strings1));


                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        etName = findViewById(R.id.et_name);
        etTel = findViewById(R.id.et_tel);
        etUp = findViewById(R.id.et_up);
        etDown = findViewById(R.id.et_down);
        tvNext = findViewById(R.id.tv_next);
    }
}

