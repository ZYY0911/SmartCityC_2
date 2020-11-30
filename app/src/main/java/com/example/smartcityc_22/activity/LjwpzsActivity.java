package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.LjwpasAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:26
 */
public class LjwpzsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView expandList;
    private List<String> strings;
    private Map<String, List<String>> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljwpzs_layout);
        initView();
        title.setText("垃圾物品展示");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        map = new HashMap<>();
        strings = new ArrayList<>();
        strings.add("可回收垃圾");
        strings.add("有害垃圾");
        strings.add("其他垃圾");
        strings.add("干垃圾");
        strings.add("湿垃圾");
        List<String> stringss = new ArrayList<>();
        stringss.add("纸");
        stringss.add("玻璃");
        map.put(strings.get(0), stringss);
        List<String> stringss1 = new ArrayList<>();
        stringss1.add("电池");
        stringss1.add("塑料袋");
        map.put(strings.get(1), stringss1);


        List<String> stringss2 = new ArrayList<>();
        stringss2.add("电器");
        stringss2.add("手机");
        map.put(strings.get(2), stringss2);


        List<String> stringss3 = new ArrayList<>();
        stringss3.add("苹果核");
        stringss3.add("香蕉皮");
        map.put(strings.get(3), stringss3);


        List<String> stringss4 = new ArrayList<>();
        stringss4.add("剩饭");
        stringss4.add("菜叶子");
        stringss4.add("生菜");
        map.put(strings.get(4), stringss4);

        expandList.setAdapter(new LjwpasAdapter(strings, map));
        expandList.setGroupIndicator(null);
        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LjqxDetialsActivity.newInstance(map.get(strings.get(groupPosition)).get(childPosition)
                        , LjwpzsActivity.this);
                return false;
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        expandList = findViewById(R.id.expand_list);
    }
}
