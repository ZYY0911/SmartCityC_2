package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.BusTitle;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 10:50
 */
public class Dzbc4Activity extends AppCompatActivity {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private TextView tvName;
    private TextView tvTel;
    private TextView tvUp;
    private TextView tvDown;
    private TextView tvRl;
    private TextView btNext;

    public static void newInstance(BusTitle busTitle, String time,
                                   String name,
                                   String tel,
                                   String up, String down, Context context) {
        Intent intent = new Intent(context, Dzbc4Activity.class);
        intent.putExtra("info", busTitle);
        intent.putExtra("time", time);
        intent.putExtra("name", name);
        intent.putExtra("tel", tel);
        intent.putExtra("up", up);
        intent.putExtra("down", down);
        context.startActivity(intent);
    }

    BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout4);
        AppClient.add(this);
        initView();
        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLine.setText("乘车线路：" + busTitle.getStartSite() + "-" + busTitle.getEndSite());
        tvRl.setText(getIntent().getStringExtra("time"));
        tvName.setText("乘客姓名：" + getIntent().getStringExtra("name"));
        tvTel.setText("乘客电话：" + getIntent().getStringExtra("tel"));
        tvUp.setText("上车地点：" + getIntent().getStringExtra("up"));
        tvDown.setText("下车地点：" + getIntent().getStringExtra("down"));
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //{busid:1,"name":"abc","phone":"1234","upsite":"火车站","downsite":"阳光新天地","traveltime":"2020-10-5,2020-10-6","isPay":"N"}
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setOrderBus")
                        .setJsonObject("busid", busTitle.getBusid())
                        .setJsonObject("name", getIntent().getStringExtra("name"))
                        .setJsonObject("phone", getIntent().getStringExtra("tel"))
                        .setJsonObject("upsite", getIntent().getStringExtra("up"))
                        .setJsonObject("downsite", getIntent().getStringExtra("down"))
                        .setJsonObject("traveltime", getIntent().getStringExtra("time"))
                        .setJsonObject("isPay", "N")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("提交成功", Dzbc4Activity.this);
                                    AppClient.finAll();
                                } else {
                                    Util.showToast("提交失败", Dzbc4Activity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("提交失败", Dzbc4Activity.this);
                            }
                        }).start();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        tvName = findViewById(R.id.tv_name);
        tvTel = findViewById(R.id.tv_tel);
        tvUp = findViewById(R.id.tv_up);
        tvDown = findViewById(R.id.tv_down);
        tvRl = findViewById(R.id.tv_rl);
        btNext = findViewById(R.id.bt_next);
    }
}
