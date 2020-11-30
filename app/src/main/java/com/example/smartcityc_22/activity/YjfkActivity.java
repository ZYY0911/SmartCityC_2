package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;

import org.json.JSONObject;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 14:53
 */
public class YjfkActivity extends AppCompatActivity {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etInput;
    private TextView tvCount;
    private Button btSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk_layout);
        initView();
        title.setText("意见反馈");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String msg = s.toString();
                    if (msg.length() >= 151) {
                        etInput.setText(msg.substring(0, 150));
                        etInput.setSelection(150);
                        Util.showToast("只能输入150字", YjfkActivity.this);
                        return;
                    }
                    tvCount.setText(msg.length() + "/150字");

                }
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etInput.getText())) {
                    Util.showToast("意见不能为空", YjfkActivity.this);
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("publicOpinion")
                        //{"userid":"1","content":"内容","time":"yyyy.MM.dd HH:mm:ss"}
                        .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                        .setJsonObject("content", etInput.getText().toString())
                        .setJsonObject("time", Util.simpleDate("yyyy.MM.dd HH:mm:ss", new Date()))
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("提交成功", YjfkActivity.this);
                                    etInput.setText("");
                                } else {
                                    Util.showToast("提交失败", YjfkActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("提交失败", YjfkActivity.this);
                            }
                        }).start();
            }
        });
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etInput = findViewById(R.id.et_input);
        tvCount = findViewById(R.id.tv_count);
        btSubmit = findViewById(R.id.bt_submit);
    }
}
