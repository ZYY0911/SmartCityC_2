package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.RlBean;
import com.example.smartcityc_22.util.MyOnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 10:35
 */
public class RlAdapter extends ArrayAdapter<RlBean> {

    public RlAdapter(@NonNull Context context, @NonNull List<RlBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.rl_item, parent, false);
        LinearLayout bgColor;
        TextView itemMsg;
        bgColor = convertView.findViewById(R.id.bg_color);
        itemMsg = convertView.findViewById(R.id.item_msg);
        final RlBean rlBean = getItem(position);
        switch (rlBean.getBg()) {
            case 1:
            case 3:
                bgColor.setBackgroundResource(R.drawable.bg_rl_1);
                break;
            case 0:
                bgColor.setBackgroundResource(R.drawable.bg_rl_3);
                break;
            case 2:
                bgColor.setBackgroundResource(R.drawable.bg_rl_2);
                break;
        }
        bgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rlBean.getBg()!=3){
                    myOnClickItem.myClick(position,"");
                }
            }
        });
        if (rlBean.getDay() != 0) {
            itemMsg.setText(rlBean.getDay() + "");
        } else {
            itemMsg.setText("");
        }
        return convertView;
    }


    private MyOnClickItem myOnClickItem;

    public void setMyOnClickItem(MyOnClickItem myOnClickItem) {
        this.myOnClickItem = myOnClickItem;
    }

    private void initView() {


    }
}
