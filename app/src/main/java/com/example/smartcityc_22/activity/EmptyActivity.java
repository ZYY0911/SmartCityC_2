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
 * @Create by 张瀛煜 on 2020/10/29 at 9:05
 */
public class EmptyActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;

    public static void newInstance(String msg, Context context) {
        Intent intent = new Intent(context, EmptyActivity.class);
        intent.putExtra("info", msg);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_layout);
        initView();
        title.setText(getIntent().getStringExtra("info"));
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
    }
}
