package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 17:08
 */
public class AddGutopActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private RadioButton rbMy;
    private RadioButton rbFm;
    private RadioButton rbPy;
    private RadioButton rbFd;
    private RadioButton rbQt;
    private EditText etMsg;
    private TextView tvNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgrour_layout);

        initView();
        title.setText("添加户号");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setAccountGroup")
                        //{"userid":"1","groupName":"单位"}
                        .setJsonObject("userid", 1)
                        .setJsonObject("groupName", rbFd.isChecked() ? rbFd.getText() : rbFm.isChecked() ? rbFm.getText()
                                : rbMy.isChecked() ? rbMy.getText() : rbPy.isChecked() ? rbPy.getText() : rbQt.isChecked() ? etMsg.getText() : "")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("添加成功", AddGutopActivity.this);
                                } else {
                                    Util.showToast("添加失败", AddGutopActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("添加失败", AddGutopActivity.this);
                            }
                        }).start();

            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        rbMy = findViewById(R.id.rb_my);
        rbFm = findViewById(R.id.rb_fm);
        rbPy = findViewById(R.id.rb_py);
        rbFd = findViewById(R.id.rb_fd);
        rbQt = findViewById(R.id.rb_qt);
        etMsg = findViewById(R.id.et_msg);
        tvNext = findViewById(R.id.tv_next);
    }
}
