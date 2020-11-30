package com.example.smartcityc_22.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.util.Util;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:48
 */
public class YysmhsActivitry extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutTeim;
    private TextView tvSj;
    private EditText tvName;
    private EditText tvTel;
    private Button btSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yysmhs_layout);
        initView();
        title.setText("预约上门回收");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTel.setText("");
                tvName.setText("");
                tvSj.setText("");
                Util.showDialog("提交成功", YysmhsActivitry.this);
            }
        });
        layoutTeim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(YysmhsActivitry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(YysmhsActivitry.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tvSj.setText(year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00");
                            }
                        }, 9, 14, true);
                        timePickerDialog.show();
                    }
                }, 2020, 9, 27);
                datePickerDialog.show();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        layoutTeim = findViewById(R.id.layout_teim);
        tvSj = findViewById(R.id.tv_sj);
        tvName = findViewById(R.id.tv_name);
        tvTel = findViewById(R.id.tv_tel);
        btSubmit = findViewById(R.id.bt_submit);
    }
}
