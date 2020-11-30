package com.example.smartcityc_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.activity.FjhsjActivity;
import com.example.smartcityc_22.activity.LjqxDetialsActivity;
import com.example.smartcityc_22.activity.LjwpzsActivity;
import com.example.smartcityc_22.activity.XxylActivity;
import com.example.smartcityc_22.activity.YyshlsActivity;
import com.example.smartcityc_22.activity.YysmhsActivitry;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:13
 */
public class SmartHbFragment extends Fragment {
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private LinearLayout layoutLjwpzs;
    private LinearLayout layoutSmhs;
    private LinearLayout layoutYyls;
    private LinearLayout layoutXxyl;
    private LinearLayout layoutFjhs;

    public SmartHbFragment() {
    }

    private AppMainActivity appMainActivity;

    public SmartHbFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static SmartHbFragment newInstance(AppMainActivity appMainActivity) {
        return new SmartHbFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_hb_fragmnet, container, false);
    }


    private int image[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};

    List<String> strings;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        strings = new ArrayList<>();
        strings.add("可回收垃圾");
        strings.add("有害垃圾");
        strings.add("其他垃圾");
        strings.add("干垃圾");
        strings.add("湿垃圾");
        title.setText("智慧环保");
        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(image[i]);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LjqxDetialsActivity.newInstance(strings.get(finalI), getActivity());
                }
            });
            viewFlipper.addView(imageView);
        }
        layoutLjwpzs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LjwpzsActivity.class));
            }
        });
        layoutSmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YysmhsActivitry.class));
            }
        });
        layoutYyls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YyshlsActivity.class));
            }
        });
        layoutFjhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FjhsjActivity.class));
            }
        });
        layoutXxyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), XxylActivity.class));
            }
        });

    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        viewFlipper = getView().findViewById(R.id.view_flipper);
        layoutLjwpzs = getView().findViewById(R.id.layout_ljwpzs);
        layoutSmhs = getView().findViewById(R.id.layout_smhs);
        layoutYyls = getView().findViewById(R.id.layout_yyls);
        layoutXxyl = getView().findViewById(R.id.layout_xxyl);
        layoutFjhs = getView().findViewById(R.id.layout_fjhs);
    }
}
