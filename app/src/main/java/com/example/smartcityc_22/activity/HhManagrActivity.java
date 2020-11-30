package com.example.smartcityc_22.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.HhglAdapter;
import com.example.smartcityc_22.bean.HdDetials;
import com.example.smartcityc_22.bean.HoItem;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.MyOnClickItem;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:53
 */
public class HhManagrActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView hhExpand;
    private Button addNew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhgl_managet_layout);
        initView();
        title.setText("户号管理");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HhManagrActivity.this, AddGutopActivity.class));
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        setVoley();

    }

    List<HoItem> hoItems;

    private void setVoley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("accountGroup")
                .setJsonObject("userid", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hoItems = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HoItem>>() {
                                }.getType());
                        setVolley_Detail();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<HdDetials> hdDetials;

    private void setVolley_Detail() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("accountByUserId")
                .setJsonObject("userid", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdDetials = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HdDetials>>() {
                                }.getType());
                        setDate();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private Map<HoItem, List<HdDetials>> map;

    private void setDate() {
        map = new HashMap<>();
        for (int i = 0; i < hoItems.size(); i++) {
            HoItem accoutGroup = hoItems.get(i);
            List<HdDetials> detailsList = new ArrayList<>();
            for (int j = 0; j < hdDetials.size(); j++) {
                HdDetials hoDetail = hdDetials.get(j);
                if (hoDetail.getGroupId() == accoutGroup.getIndex()) {
                    detailsList.add(hoDetail);
                }
            }
            map.put(accoutGroup, detailsList);
        }
        HhglAdapter adapter = new HhglAdapter(hoItems, map);
        adapter.setMyOnClickItem(new MyOnClickItem() {
            @Override
            public void myClick(int position, String name) {
                startActivity(new Intent(HhManagrActivity.this, AddHome.class));
            }
        });
        hhExpand.setAdapter(adapter);
        hhExpand.setGroupIndicator(null);

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        hhExpand = findViewById(R.id.hh_expand);
        addNew = findViewById(R.id.add_new);
    }
}
