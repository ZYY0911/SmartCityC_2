package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.TccInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 9:31
 */
public class TccDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemJl;
    private TextView itemDwkf;
    private TextView tvMsg;

    public static void newInstance(TccInfo tccInfo, Context context) {
        Intent intent = new Intent(context, TccDetailsActivity.class);
        intent.putExtra("info", tccInfo);
        context.startActivity(intent);
    }


    private TccInfo tccInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc_details);
        tccInfo = (TccInfo) getIntent().getSerializableExtra("info");
        initView();
        title.setText("停车场详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        itemName.setText(tccInfo.getParkName());
        itemAddress.setText(tccInfo.getAddress());
        itemJl.setText("距离：" + tccInfo.getDistance());
        itemDwkf.setText("是否对外开放：" + (tccInfo.getIsOpen().equals("N") ? "不对外开放" : "对外开放"));
        tvMsg.setText("停车费：" + tccInfo.getRate() + "\n剩余车位：" + tccInfo.getSurCarPort() + "\n参考收费：" + tccInfo.getRateRefer());
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        itemName = findViewById(R.id.item_name);
        itemAddress = findViewById(R.id.item_address);
        itemJl = findViewById(R.id.item_jl);
        itemDwkf = findViewById(R.id.item_dwkf);
        tvMsg = findViewById(R.id.tv_msg);
    }
}
