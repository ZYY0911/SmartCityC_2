package com.example.smartcityc_22.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.dialog.NetDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:50
 */
public class GuildFragment extends Fragment {
    private int image, lx;
    private ImageView itemImage;
    private Button btSetting;
    private Button btMain;

    public GuildFragment(int image, int lx) {
        this.image = image;
        this.lx = lx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guld_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        if (lx == 1) {
            btMain.setVisibility(View.GONE);
            btSetting.setVisibility(View.GONE);
        } else {
            btMain.setVisibility(View.VISIBLE);
            btSetting.setVisibility(View.VISIBLE);

        }
        itemImage.setImageResource(image);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AppMainActivity.class));
                getActivity().finish();
            }
        });

        btSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetDialog dialog = new NetDialog();
                dialog.show(getChildFragmentManager(), "");
            }
        });
    }


    private void initView() {
        itemImage = getView().findViewById(R.id.item_image);
        btSetting = getView().findViewById(R.id.bt_setting);
        btMain = getView().findViewById(R.id.bt_main);
    }
}
