package com.example.smartcityc_22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.RlAdapter;
import com.example.smartcityc_22.bean.BusTitle;
import com.example.smartcityc_22.bean.RlBean;
import com.example.smartcityc_22.util.MyOnClickItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 10:29
 */
public class Dzbc2Activity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private GridView girdView;
    private TextView tvMsg;
    private TextView btNext;


    public static void newInstance(BusTitle busTitle, Context context) {
        Intent intent = new Intent(context, Dzbc2Activity.class);
        intent.putExtra("info", busTitle);
        context.startActivity(intent);
    }

    List<RlBean> rlBeans, rlBeansList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout2);
        initView();
        rlBeans = new ArrayList<>();
        integers = new ArrayList<>();
        rlBeansList = new ArrayList<>();
        AppClient.add(this);
        setDate();
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private List<Integer> integers;
    private String chooseTime;

    private void setDate() {

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < calendar.get(Calendar.DAY_OF_WEEK) - 1; i++) {
            rlBeans.add(new RlBean(i, 0, 0, 3));
            integers.add(3);
        }
        for (int i = 0; i < 42; i++) {
            rlBeans.add(new RlBean(i, calendar.get(Calendar.DAY_OF_MONTH), (calendar.get(Calendar.DAY_OF_MONTH) + 1)
                    , getBg(calendar)));
            integers.add(getBg(calendar));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        int count = 49 - rlBeans.size();
        if (rlBeans.size() != 42) {
            for (int i = 0; i < count; i++) {
                rlBeans.add(new RlBean(i, 0, 0, 3));
                integers.add(3);
            }
        }
        final RlAdapter adapter = new RlAdapter(this, rlBeans);
        girdView.setAdapter(adapter);
        adapter.setMyOnClickItem(new MyOnClickItem() {
            @Override
            public void myClick(int position, String name) {
                RlBean rlBean = rlBeans.get(position);
                if (rlBean.getBg() == 2) {
                    rlBeansList.remove(rlBean);
                    rlBean.setBg(integers.get(position));
                } else {
                    rlBean.setBg(2);
                    rlBeansList.add(rlBean);
                }
                chooseTime = "";
                Collections.sort(rlBeansList, new Comparator<RlBean>() {
                    @Override
                    public int compare(RlBean o1, RlBean o2) {
                        return o1.getIndex() - o2.getIndex();
                    }
                });
                for (int i = 0; i < rlBeansList.size(); i++) {
                    RlBean rlBean1 = rlBeansList.get(i);
                    if (i == 0) {
                        chooseTime = "2020-" + rlBean1.getMonth() + "-" + rlBean1.getDay();
                    } else {
                        chooseTime += ",2020-" + rlBean1.getMonth() + "-" + rlBean1.getDay();
                    }
                }
                tvMsg.setText("已选择日期：\n" + chooseTime);
                rlBeans.set(position, rlBean);
                adapter.notifyDataSetChanged();
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dzbc3Activity.newInstance((BusTitle) getIntent().getSerializableExtra("info"), chooseTime, Dzbc2Activity.this);

            }
        });
    }

    private int getBg(Calendar calendar) {
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == 7 || week == 1) {
            return 1;
        } else {
            return 0;
        }
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        girdView = findViewById(R.id.gird_view);
        tvMsg = findViewById(R.id.tv_msg);
        btNext = findViewById(R.id.bt_next);
    }
}
