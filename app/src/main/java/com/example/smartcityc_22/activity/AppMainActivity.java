package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.fragment.EmptyFramgenet;
import com.example.smartcityc_22.fragment.HomeFragment;
import com.example.smartcityc_22.fragment.MotifPwdFragment;
import com.example.smartcityc_22.fragment.MyCenterFragment;
import com.example.smartcityc_22.fragment.MyInfoFragment;
import com.example.smartcityc_22.fragment.ShjfFragment;
import com.example.smartcityc_22.fragment.SmartBus;
import com.example.smartcityc_22.fragment.SmartHbFragment;
import com.example.smartcityc_22.fragment.TccFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AppMainActivity extends AppCompatActivity {

    private EditText etSearch;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNav;
    private LinearLayout layoutHead;
    public Map<String, Fragment> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        map = new HashMap<>();
        map.put("1", HomeFragment.newInstance(this));
        map.put("停车场", TccFragment.newInstance(this));
        map.put("智慧巴士", SmartBus.newInstance(this));
        map.put("个人中心", MyCenterFragment.newInstance(this));
        map.put("个人信息", MyInfoFragment.newInstance(this));
        map.put("修改密码", MotifPwdFragment.newInstance(this));
        map.put("全部服务", EmptyFramgenet.newInstance(this, "全部服务"));
        map.put("新闻", EmptyFramgenet.newInstance(this, "新闻"));
        map.put("智慧环保", SmartHbFragment.newInstance(this));
        map.put("生活缴费", new ShjfFragment(this));


        switchFragment(map.get("1"));
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        switchFragment(map.get("1"));
                        break;
                    case R.id.action_center:
                        switchFragment(map.get("个人中心"));
                        break;
                    case R.id.action_new:
                        switchFragment(map.get("新闻"));
                        break;
                    case R.id.action_service:
                        switchFragment(map.get("全部服务"));
                        break;
                    case R.id.action_fp:
                        switchFragment(map.get("智慧环保"));
                        break;
                }
                return true;
            }
        });
    }

    Fragment currentFragment = new Fragment();

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame_layout, fragment, fragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(fragment);
        }
        if (fragment.getClass().getName().equals(map.get("1").getClass().getName())) {
            layoutHead.setVisibility(View.VISIBLE);
        } else {
            layoutHead.setVisibility(View.GONE);
        }
        currentFragment = fragment;
        transaction.commit();


    }


    public void onBack() {
        switchFragment(map.get("1"));

    }

    private void initView() {
        etSearch = findViewById(R.id.et_search);
        frameLayout = findViewById(R.id.frame_layout);
        bottomNav = findViewById(R.id.bottom_nav);
        layoutHead = findViewById(R.id.layout_head);
    }
}