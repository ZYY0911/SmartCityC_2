package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.BusTitle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 10:18
 */
public class DzbcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView ivPhoto;
    private TextView tvMsg;
    private Button btNext;

    public static void newInstance(BusTitle busTitle, Context context) {
        Intent intent = new Intent(context, DzbcActivity.class);
        intent.putExtra("info", busTitle);
        context.startActivity(intent);
    }

    private BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout);

        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        initView();
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivPhoto.setImageResource(busTitle.getBusid() % 2 == 0 ? R.mipmap.ditu : R.mipmap.ditu2);
        tvMsg.setText("起点：" + busTitle.getStartSite() + "\n终点：" + busTitle.getEndSite() + "" +
                "\n票价：" + busTitle.getPrice() + "￥\n里程：" + busTitle.getMileage());
        AppClient.add(this);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dzbc2Activity.newInstance(busTitle, DzbcActivity.this);
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        btNext = findViewById(R.id.bt_next);
    }
}
