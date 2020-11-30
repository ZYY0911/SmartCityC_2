package com.example.smartcityc_22.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 7:47
 */
public class MyListView extends GridView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
