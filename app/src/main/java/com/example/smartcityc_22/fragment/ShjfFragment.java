package com.example.smartcityc_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.activity.DfActivity;
import com.example.smartcityc_22.activity.HhManagrActivity;
import com.example.smartcityc_22.activity.SfActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:41
 */
public class ShjfFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvHhgl;
    private LinearLayout layoutSf;
    private LinearLayout layouDf;
    private LinearLayout layoutHhgl;
    private LinearLayout layoutXgzx;

    public ShjfFragment() {
    }

    private AppMainActivity appMainActivity;

    public ShjfFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static ShjfFragment newInstance(AppMainActivity appMainActivity) {
        return new ShjfFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shjf_layout, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("德州 生活缴费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.onBack();
            }
        });
        layoutHhgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HhManagrActivity.class));
            }
        });
        tvHhgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HhManagrActivity.class));
            }
        });
        layouDf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DfActivity.class));
            }
        });
        layoutSf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SfActivity.class));
            }
        });
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        tvHhgl = getView().findViewById(R.id.tv_hhgl);
        layoutSf = getView().findViewById(R.id.layout_sf);
        layouDf = getView().findViewById(R.id.layou_df);
        layoutHhgl = getView().findViewById(R.id.layout_hhgl);
        layoutXgzx = getView().findViewById(R.id.layout_xgzx);
    }
}
