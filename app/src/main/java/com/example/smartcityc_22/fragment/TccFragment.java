package com.example.smartcityc_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.activity.TccDetailsActivity;
import com.example.smartcityc_22.activity.TccHistoryActivity;
import com.example.smartcityc_22.adapter.TccAdapter;
import com.example.smartcityc_22.bean.TccInfo;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.MyOnClickItem;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 9:08
 */
public class TccFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Button btMore;

    public TccFragment() {
    }

    private AppMainActivity appMainActivity;

    public TccFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;

    }

    public static TccFragment newInstance(AppMainActivity appMainActivity) {
        return new TccFragment(appMainActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tvv_layout, container, false);
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
        title.setText("停车场");
        title1.setText("列表");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TccHistoryActivity.class));
            }
        });
        setVolley();
        btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCount(tccInfos.size());
                adapter.notifyDataSetChanged();
                Util.showToast("加载成功", getContext());
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setVolley();
        }
        super.onHiddenChanged(hidden);
    }

    List<TccInfo> tccInfos;
    Random random = new Random();
    TccAdapter adapter;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getParkInfor")
                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tccInfos = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<TccInfo>>() {
                                }.getType());

                        Collections.sort(tccInfos, new Comparator<TccInfo>() {
                            @Override
                            public int compare(TccInfo o1, TccInfo o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });
                        int count = random.nextInt(1);
                        count = 5 + count;
                        if (count > tccInfos.size()) {
                            count = tccInfos.size();
                        }
                        adapter = new TccAdapter(getActivity(), tccInfos, count);
                        listView.setAdapter(adapter);
                        adapter.setMyOnClickItem(new MyOnClickItem() {
                            @Override
                            public void myClick(int position, String name) {
                                if (name.equals("1")) {
                                    TccHistoryActivity.newInstance(tccInfos.get(position), getActivity());
                                } else {
                                    TccDetailsActivity.newInstance(tccInfos.get(position), getActivity());
                                }
                            }});

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
        listView = getView().findViewById(R.id.list_view);
        btMore = getView().findViewById(R.id.bt_more);
    }
}
