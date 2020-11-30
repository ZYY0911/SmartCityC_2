package com.example.smartcityc_22.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcityc_22.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:25
 */
public class LjflFragment extends Fragment {
    private String title, msg;
    private TextView itemName;
    private TextView itemMsg;

    public LjflFragment(String title, String msg) {
        this.title = title;
        this.msg = msg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fjhs_fragment_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        itemName.setText(title);
        itemMsg.setText(msg);
    }

    private void initView() {
        itemName = getView().findViewById(R.id.item_name);
        itemMsg = getView().findViewById(R.id.item_msg);
    }
}
