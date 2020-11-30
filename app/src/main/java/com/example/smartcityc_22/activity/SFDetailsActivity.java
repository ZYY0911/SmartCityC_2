package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.HdDetials;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/9 at 8:02
 */
public class SFDetailsActivity extends AppCompatActivity {
    private HdDetials hoDetails;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvState;
    private TextView tvText;

    public static void newInstance(HdDetials hdDetials, Context context) {
        Intent intent = new Intent(context, SFDetailsActivity.class);
        intent.putExtra("info", hdDetials);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sf_details_layout);
        initView();
        title.setText("水费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hoDetails = (HdDetials) getIntent().getSerializableExtra("info");
        int baleance = hoDetails.getBanlance();
        int cost = hoDetails.getCost();
        if (cost > baleance) {
            tvState.setText("欠费:" + (cost - baleance));
        } else {
            tvState.setText("暂无欠费");
        }
        tvText.setText("缴费单位：" + hoDetails.getUnit() + "\n" + "缴费户号：" + hoDetails.getAccountId() + "|" + hoDetails.getAccountAddress());

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvState = findViewById(R.id.tv_state);
        tvText = findViewById(R.id.tv_text);
    }
}
