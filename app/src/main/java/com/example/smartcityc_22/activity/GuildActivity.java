package com.example.smartcityc_22.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartcityc_22.AppClient;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.fragment.GuildFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:48
 */
public class GuildActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout layout;

    private List<Fragment> fragments;
    private int image[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guild_layout);
        initView();
        if (AppClient.sharedPreferences.getBoolean(AppClient.IsFirst, true)) {
            AppClient.sharedPreferences.edit().putBoolean(AppClient.IsFirst, false).apply();
        } else {
            startActivity(new Intent(GuildActivity.this, AppMainActivity.class));
            finish();
            return;
        }
        fragments = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            if (i != image.length - 1) {
                fragments.add(new GuildFragment(image[i], 1));
            } else {
                fragments.add(new GuildFragment(image[i], 2));
            }
            ImageView imageView = new ImageView(GuildActivity.this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.selset_image);
            } else {
                imageView.setImageResource(R.drawable.no_selset_image);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(60, 0, 60, 0);
            imageView.setLayoutParams(layoutParams);
            layout.addView(imageView);
        }
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    ImageView imageView = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        imageView.setImageResource(R.drawable.selset_image);
                    } else {
                        imageView.setImageResource(R.drawable.no_selset_image);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        layout = findViewById(R.id.layout);
    }
}
