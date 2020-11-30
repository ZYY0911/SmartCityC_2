package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:40
 */
public class LjqxDetialsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView ivPhoto;
    private TextView tvMsg;

    public static void newInstance(String msg, Context context) {
        Intent intent = new Intent(context, LjqxDetialsActivity.class);
        intent.putExtra("info", msg);
        context.startActivity(intent);
    }

    String msg = "       可回收垃圾一般指可回收物。可回收物指可以再生循环的垃圾。本身或材质可再利用的纸类、硬纸板、玻璃、塑料、金属、塑料包装，与这些材质有关的，如：报纸、杂志、广告单及其它干净的纸类等皆可回收";

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljwpzs_detials_layout);
        initView();
        title.setText(getIntent().getStringExtra("info"));
        tvMsg.setText(getIntent().getStringExtra("info") + "\n" + msg);
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
    }
}

