package com.example.smartcityc_22.util;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:28
 */
public class ViewTeanslation implements ViewPager.PageTransformer {
    private ViewPager viewPager;
    private int offsetX;

    public ViewTeanslation(Context context) {
        offsetX = (int) (180 * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) view.getParent();
        }

        int leftInScreen = view.getLeft() - viewPager.getScrollX();
        int centerXInViewPager = leftInScreen + view.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - viewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.38f / viewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);
        if (scaleFactor > 0) {
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setTranslationX(-offsetX * offsetRate);
        }

    }
}
