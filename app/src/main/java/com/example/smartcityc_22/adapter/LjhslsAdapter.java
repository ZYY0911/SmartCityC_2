package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.LjHistory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:07
 */
public class LjhslsAdapter extends ArrayAdapter<LjHistory> {

    public LjhslsAdapter(@NonNull Context context, @NonNull List<LjHistory> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.yyhsls_item, parent, false);
        TextView itemXh;
        TextView itemRq;
        TextView itemLx;
        TextView itemJg;
        itemXh = convertView.findViewById(R.id.item_xh);
        itemRq = convertView.findViewById(R.id.item_rq);
        itemLx = convertView.findViewById(R.id.item_lx);
        itemJg = convertView.findViewById(R.id.item_jg);
        LjHistory ljHistory = getItem(position);
        itemXh.setText(ljHistory.getInidex() + "");
        itemRq.setText(ljHistory.getRq());
        itemLx.setText(ljHistory.getLx());
        itemJg.setText(ljHistory.getJe() + "");
        return convertView;
    }

    private void initView() {


    }
}
