package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.fragment.LjflFragment;
import com.example.smartcityc_22.util.ViewTeanslation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:14
 */
public class FjhsjActivity extends AppCompatActivity {
    private Map<String, List<String>> map;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView expandList;
    private ViewPager viewPager;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fjhsj_layout);
        initView();
        title.setText("附近回收机");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragments = new ArrayList<>();
        fragments.add(new LjflFragment("一号回收机器", "衣服     5元/件\n瓶子      0.5元/个"));
        fragments.add(new LjflFragment("二号回收机器", "衣服     10元/件\n瓶子      0.6元/个"));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.setPageTransformer(false, new ViewTeanslation(this));
//        map = new HashMap<>();
//        List<String> strings = new ArrayList<>();
//        strings.add("衣服     5元/件");
//        strings.add("瓶子      0.5元/个");
//        map.put("一号回收机器", strings);
//        List<String> strings1 = new ArrayList<>();
//        strings1.add("衣服     5元/件");
//        strings1.add("瓶子      0.5元/个");
//        map.put("二号回收机器", strings1);
//        expandList.setAdapter(new FjHsjAdatper(map));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        //expandList = findViewById(R.id.expand_list);
        viewPager = findViewById(R.id.view_pager);
    }
}
