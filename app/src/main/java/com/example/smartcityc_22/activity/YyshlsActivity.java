package com.example.smartcityc_22.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.LjhslsAdapter;
import com.example.smartcityc_22.bean.LjHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:59
 */
public class YyshlsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutStart;
    private TextView etStart;
    private LinearLayout layoutEnd;
    private TextView etEnd;
    private Button btQuery;
    private ListView listView;

    private List<LjHistory> ljHistories, ljHistoryList;

    private LjhslsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyhsls_layout);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("预约垃圾回收历史");

        layoutEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose(etEnd);
            }
        });
        layoutStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose(etStart);
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start = etStart.getText().toString();
                String end = etEnd.getText().toString();
                Date date, date1, date2;
                ljHistoryList.clear();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (start.equals("")) {
                    try {
                        date = format.parse(end);
                        for (int i = 0; i < ljHistories.size(); i++) {
                            LjHistory ljHistory = ljHistories.get(i);
                            date1 = format.parse(ljHistory.getRq());
                            if (date1.before(date)) {
                                ljHistoryList.add(ljHistory);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (end.equals("")) {
                    try {
                        date = format.parse(start);
                        for (int i = 0; i < ljHistories.size(); i++) {
                            LjHistory ljHistory = ljHistories.get(i);
                            date1 = format.parse(ljHistory.getRq());
                            if (date1.after(date)) {
                                ljHistoryList.add(ljHistory);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        date = format.parse(start);
                        date1 = format.parse(end);
                        for (int i = 0; i < ljHistories.size(); i++) {
                            LjHistory ljHistory = ljHistories.get(i);
                            date2 = format.parse(ljHistory.getRq());
                            if (date2.after(date) && date2.before(date1)) {
                                ljHistoryList.add(ljHistory);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        ljHistories = new ArrayList<>();
        ljHistories.add(new LjHistory(1, "2020-10-1 12:23:12", "厨余垃圾", 100));
        ljHistories.add(new LjHistory(2, "2020-10-2 13:23:12", "有害垃圾", 400));
        ljHistories.add(new LjHistory(3, "2020-10-4 20:23:12", "干垃圾", 10));
        ljHistoryList = new ArrayList<>();
        ljHistoryList.addAll(ljHistories);
        adapter = new LjhslsAdapter(this,
                ljHistoryList);
        listView.setAdapter(adapter);

    }

    private void showChoose(final TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(YyshlsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(YyshlsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00");
                    }
                }, 9, 14, true);
                timePickerDialog.show();
            }
        }, 2020, 9, 27);
        datePickerDialog.show();
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        layoutStart = findViewById(R.id.layout_start);
        etStart = findViewById(R.id.et_start);
        layoutEnd = findViewById(R.id.layout_end);
        etEnd = findViewById(R.id.et_end);
        btQuery = findViewById(R.id.bt_query);
        listView = findViewById(R.id.list_view);
    }
}
