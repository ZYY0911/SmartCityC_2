package com.example.smartcityc_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.activity.MyOrderActivity;
import com.example.smartcityc_22.activity.YjfkActivity;
import com.example.smartcityc_22.bean.UserInfo;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.NetImageView;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 11:22
 */
public class MyCenterFragment extends Fragment {

    private TextView title;
    private TextView title1;
    private NetImageView ivPhoto;
    private TextView tvName;
    private LinearLayout layoutInfo;
    private LinearLayout layoutOrder;
    private LinearLayout layoutPwd;
    private LinearLayout layoutYjfk;
    private Button btExit;

    public MyCenterFragment() {
    }

    private AppMainActivity appMainActivity;

    public MyCenterFragment(AppMainActivity appMainActivity) {

        this.appMainActivity = appMainActivity;
    }

    public static MyCenterFragment newInstance(AppMainActivity appMainActivity) {
        return new MyCenterFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_center_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("个人中心");
        setVolley_Info();
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AppMainActivity.class));
                appMainActivity.finish();
            }
        });
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.switchFragment(appMainActivity.map.get("个人信息"));
            }
        });
        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
            }
        });
        layoutPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.switchFragment(appMainActivity.map.get("修改密码"));
            }
        });
        layoutYjfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YjfkActivity.class));
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setVolley_Info();
        }
        super.onHiddenChanged(hidden);
    }

    public UserInfo userInfo;

    private void setVolley_Info() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setDialog(getContext())
                .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , UserInfo.class);
                        ivPhoto.setMyUrl(userInfo.getAvatar());
                        tvName.setText("昵称：" + userInfo.getName());

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        ivPhoto = getView().findViewById(R.id.iv_photo);
        tvName = getView().findViewById(R.id.tv_name);
        layoutInfo = getView().findViewById(R.id.layout_info);
        layoutOrder = getView().findViewById(R.id.layout_order);
        layoutPwd = getView().findViewById(R.id.layout_pwd);
        layoutYjfk = getView().findViewById(R.id.layout_yjfk);
        btExit = getView().findViewById(R.id.bt_exit);
    }
}
