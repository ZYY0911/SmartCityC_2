package com.example.smartcityc_22.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.activity.AppMainActivity;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 14:46
 */
public class MotifPwdFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etPwd;
    private EditText etNewPwd;
    private EditText etNewPwd2;
    private Button btSave;

    public MotifPwdFragment() {
    }

    private AppMainActivity appMainActivity;

    public MotifPwdFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }


    public static MotifPwdFragment newInstance(AppMainActivity appMainActivity) {
        return new MotifPwdFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.motif_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("修改密码");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.switchFragment(appMainActivity.map.get("个人中心"));
            }
        });
        setView();
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPwd.getText()) || TextUtils.isEmpty(etNewPwd.getText())) {
                    Util.showDialog("密码不能为空", getContext());
                    return;
                }
                String pwd = etPwd.getText().toString();
                String pwd1 = etNewPwd.getText().toString();
                String pwd2 = etNewPwd2.getText().toString();

                if (!pwd1.equals(pwd2)) {
                    Util.showDialog("两次密码不一致", getContext());
                    return;
                }
                if (!pwd.equals(passWord)) {
                    Util.showDialog("原密码不正确", getContext());
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setPwd")
                        .setDialog(getContext())
                        .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                        .setJsonObject("password", pwd1)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("修改成功", getActivity());
                                } else {
                                    Util.showToast("修改失败", getActivity());
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("修改失败", getActivity());
                            }
                        }).start();

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setView();
        }
        super.onHiddenChanged(hidden);
    }

    private String passWord;

    private void setView() {
        etNewPwd.setText("");
        etNewPwd2.setText("");
        etPwd.setText("");
        setVolley();


    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getPwd")
                .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        passWord = jsonObject.optString("password");
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
        etPwd = getView().findViewById(R.id.et_pwd);
        etNewPwd = getView().findViewById(R.id.et_new_pwd);
        etNewPwd2 = getView().findViewById(R.id.et_new_pwd2);
        btSave = getView().findViewById(R.id.bt_save);
    }
}
