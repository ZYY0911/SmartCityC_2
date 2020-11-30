package com.example.smartcityc_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.activity.DzbcActivity;
import com.example.smartcityc_22.activity.WdddActivity;
import com.example.smartcityc_22.adapter.BusExpandList;
import com.example.smartcityc_22.bean.BusStation;
import com.example.smartcityc_22.bean.BusTitle;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.MyOnClickItem;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 10:04
 */
public class SmartBus extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView expandList;

    public SmartBus() {
    }

    private AppMainActivity appMainActivity;

    public SmartBus(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static SmartBus newInstance(AppMainActivity appMainActivity) {
        return new SmartBus(appMainActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_bus, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.onBack();
            }
        });
        title.setText("智慧巴士");
        title1.setText("我的订单");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WdddActivity.class));
            }
        });
        setVolley();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setVolley();
        }
        super.onHiddenChanged(hidden);

    }

    List<BusTitle> busTitles;

    private Map<BusTitle, List<BusStation>> map;

    private void setVolley() {
        map = new HashMap<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busTitles = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<BusTitle>>() {
                                }.getType());
                        for (int i = 0; i < busTitles.size(); i++) {
                            final BusTitle busTitle = busTitles.get(i);
                            VolleyTo volleyTo1 = new VolleyTo();
                            volleyTo1.setUrl("busStationById")
                                    .setJsonObject("busid", busTitle.getBusid())
                                    .setVolleyLo(new VolleyLo() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            List<BusStation> busStations = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                                    , new TypeToken<List<BusStation>>() {
                                                    }.getType());
                                            map.put(busTitle, busStations);
                                            if (map.size() == busTitles.size()) {
                                                BusExpandList busExpandList = new BusExpandList(busTitles, map);
                                                expandList.setAdapter(busExpandList);
                                                busExpandList.setOnClickItem(new MyOnClickItem() {
                                                    @Override
                                                    public void myClick(int position, String name) {
                                                        DzbcActivity.newInstance(busTitles.get(position), getActivity());
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                        }
                                    }).start();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        expandList = getView().findViewById(R.id.expand_list);
        expandList.setGroupIndicator(null);
    }
}

