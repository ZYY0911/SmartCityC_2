package com.example.smartcityc_22.dialog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.util.Util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:59
 */
public class NetDialog extends DialogFragment {
    private EditText etIp;
    private EditText etPort;
    private Button btSubmit;
    private Button btExit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return
                inflater.inflate(R.layout.net_dialog, container, false);

    }

    SharedPreferences preferences;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        preferences = AppClient.sharedPreferences;
        etIp.setText(preferences.getString(AppClient.IP, "192.168.155.106"));
        etPort.setText(preferences.getString(AppClient.PORT, "8080"));
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putString(AppClient.IP, etIp.getText().toString()).apply();
                preferences.edit().putString(AppClient.PORT, etPort.getText().toString()).apply();
                Util.showToast("修改成功", getContext());
                getDialog().dismiss();
            }
        });

    }

    private void initView() {
        etIp = getView().findViewById(R.id.et_ip);
        etPort = getView().findViewById(R.id.et_port);
        btSubmit = getView().findViewById(R.id.bt_submit);
        btExit = getView().findViewById(R.id.bt_exit);
    }
}
