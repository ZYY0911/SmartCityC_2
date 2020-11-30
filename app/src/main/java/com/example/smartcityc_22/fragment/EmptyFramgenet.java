package com.example.smartcityc_22.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:09
 */
public class EmptyFramgenet extends Fragment {
    private TextView title;
    private TextView title1;

    public EmptyFramgenet() {

    }

    private AppMainActivity appMainActivity;
    private String name;

    public EmptyFramgenet(AppMainActivity appMainActivity, String name) {
        this.appMainActivity = appMainActivity;
        this.name = name;
    }

    public static EmptyFramgenet newInstance(AppMainActivity appMainActivity, String name) {
        return new EmptyFramgenet(appMainActivity, name);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_layout2, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText(name);
    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
    }
}
