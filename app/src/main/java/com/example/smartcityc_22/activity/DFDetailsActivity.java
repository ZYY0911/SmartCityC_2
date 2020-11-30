package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.HdDetials;
import com.example.smartcityc_22.bean.UserInfo;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/9 at 8:02
 */
public class DFDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvText;
    private HdDetials hoDetails;
    private UserInfo userInfos;

    public static void newInstance(HdDetials hdDetials, Context context) {
        Intent intent = new Intent(context, DFDetailsActivity.class);
        intent.putExtra("info", hdDetials);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.df_details_layout);
        initView();
        title.setText("电费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hoDetails = (HdDetials) getIntent().getSerializableExtra("info");

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", hoDetails.getUserid())
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfos = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , UserInfo.class);

                        int baleance = hoDetails.getBanlance();
                        int cost = hoDetails.getCost();
                        int now = baleance - cost;
                        tvText.setText("缴费单位：" + hoDetails.getUnit() + "\n缴费户号：" + hoDetails.getAccountId()
                                + "\n户名：" + userInfos.getName() + "\n地址：" + hoDetails.getAccountAddress() + "\n当前可用余额：" + baleance + "元\n" +
                                "当前欠费余额：" + (now > 0 ? "无欠费" : now + "元"));
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
        tvText = findViewById(R.id.tv_text);
    }
}
