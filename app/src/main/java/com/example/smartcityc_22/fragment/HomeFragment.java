package com.example.smartcityc_22.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.activity.EmptyActivity;
import com.example.smartcityc_22.activity.WebViewActivity;
import com.example.smartcityc_22.adapter.HomeServiceAdapter;
import com.example.smartcityc_22.adapter.NewAdapter;
import com.example.smartcityc_22.adapter.SubjectAdapter;
import com.example.smartcityc_22.bean.HomeImage;
import com.example.smartcityc_22.bean.NewsList;
import com.example.smartcityc_22.bean.ServiceOrder;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.MyGirdView;
import com.example.smartcityc_22.util.MyListView;
import com.example.smartcityc_22.util.MyOnClickItem;
import com.example.smartcityc_22.util.NetImageView;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 8:13
 */
public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private MyGirdView serviceGird;
    private MyGirdView subjectGird;
    private LinearLayout layoutNews;
    private MyListView newList;

    public HomeFragment() {
    }

    private AppMainActivity appMainActivity;

    public HomeFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }


    public static HomeFragment newInstance(AppMainActivity appMainActivity) {
        return new HomeFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragmnet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        ssetVolley_Image();
        setVolley_Service();
        setVolley_Subjce();
        setVolley_New();
    }

    List<NewsList> newsLists;

    private void setVolley_New() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setVolley_Type();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<String> newType;

    private void setVolley_Type() {
        newType = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllNewsType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Util.Row);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            newType.add(jsonArray.optJSONObject(i).optString("newstype"));
                        }
                        for (int i = 0; i < newType.size(); i++) {
                            final TextView textView = new TextView(getActivity());
                            textView.setText(newType.get(i));
                            if (i == 0) {
                                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                                textView.setBackgroundResource(R.drawable.text_line);
                            } else {
                                textView.setTextColor(Color.parseColor("#333333"));
                                textView.setBackgroundResource(R.drawable.text_no_line);
                            }
                            textView.setPadding(0, 0, 0, 10);
                            textView.setTextSize(20);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(20, 0, 20, 0);
                            textView.setLayoutParams(layoutParams);
                            final int finalI = i;
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < layoutNews.getChildCount(); j++) {
                                        TextView textView1 = (TextView) layoutNews.getChildAt(j);
                                        if (j == finalI) {
                                            textView1.setTextColor(getResources().getColor(R.color.colorPrimary));
                                            textView1.setBackgroundResource(R.drawable.text_line);
                                        } else {
                                            textView1.setTextColor(Color.parseColor("#333333"));
                                            textView1.setBackgroundResource(R.drawable.text_no_line);
                                        }
                                    }
                                    setNewType(textView.getText().toString());
                                }
                            });
                            layoutNews.addView(textView);
                        }
                        setNewType(newType.get(0));

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setNewType(String toString) {
        final List<NewsList> newLists1 = new ArrayList<>();
        for (int i = 0; i < newsLists.size(); i++) {
            NewsList newList = newsLists.get(i);
            if (newList.getNewsType().equals(toString)) {
                newLists1.add(newList);
            }
        }
        newList.setAdapter(new NewAdapter(getActivity(), newLists1));
        newList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EmptyActivity.newInstance(newLists1.get(position).getTitle(), getActivity());
            }
        });
    }

    private void setVolley_Subjce() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllSubject")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String arr = jsonObject.optString(Util.Row).replace("[", "").replace("]", "");
                        String[] arrs = arr.split(",");
                        final List<String> list = new ArrayList<>();
                        list.addAll(Arrays.asList(arrs));
                        subjectGird.setAdapter(new SubjectAdapter(getActivity(), list));
                        subjectGird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                EmptyActivity.newInstance(list.get(position), getActivity());
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<ServiceOrder> serviceOrders;

    private void setVolley_Service() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getServiceInOrder")
                .setJsonObject("order", 2)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        serviceOrders = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<ServiceOrder>>() {
                                }.getType());
                        HomeServiceAdapter adapter = new HomeServiceAdapter(getActivity(), serviceOrders);
                        serviceGird.setAdapter(adapter);
                        adapter.setOnClickItem(new MyOnClickItem() {
                            @Override
                            public void myClick(int position, String name) {
                                if (name.equals("停车场")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("停车场"));
                                } else if (name.equals("智慧巴士")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("智慧巴士"));
                                } else if (name.equals("更多服务")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("全部服务"));
                                } else if (name.equals("生活缴费")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("生活缴费"));
                                } else {
                                    WebViewActivity.newInstance(serviceOrders.get(position).getId(), getActivity());
                                }
                            }
                        });

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<HomeImage> homeImages;

    private void ssetVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homeImages = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HomeImage>>() {
                                }.getType());
                        for (int i = 0; i < homeImages.size(); i++) {
                            HomeImage homeImage = homeImages.get(i);
                            NetImageView imageView = new NetImageView(getActivity());
                            imageView.setMyUrl(homeImage.getPath());
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            final int finalI = i;
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EmptyActivity.newInstance("新闻" + (finalI + 1), getActivity());
                                }
                            });
                            viewFlipper.addView(imageView);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        viewFlipper = getView().findViewById(R.id.view_flipper);
        serviceGird = getView().findViewById(R.id.service_gird);
        subjectGird = getView().findViewById(R.id.subject_gird);
        layoutNews = getView().findViewById(R.id.layout_news);
        newList = getView().findViewById(R.id.new_list);
    }
}

