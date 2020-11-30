package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:37
 */
public class XxylActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etName;
    private EditText etTel;
    private EditText etAddress;
    private EditText etTime;
    private TextView btMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ylxx_fragment);
        initView();
        etName.setText(AppClient.name);
        etTel.setText(AppClient.tel);
        etAddress.setText(AppClient.address);
        etTime.setText(AppClient.time);
        btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClient.name = etName.getText().toString();
                AppClient.tel = etTel.getText().toString();
                AppClient.address = etAddress.getText().toString();
                AppClient.time = etTime.getText().toString();
                Toast.makeText(XxylActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        title.setText("信息预留");
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
        etName = findViewById(R.id.et_name);
        etTel = findViewById(R.id.et_tel);
        etAddress = findViewById(R.id.et_address);
        etTime = findViewById(R.id.et_time);
        btMore = findViewById(R.id.bt_more);
    }
}
