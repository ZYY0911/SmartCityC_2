package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.OrderTitle;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 11:47
 */
public
class OrderTitleAdapter extends ArrayAdapter<OrderTitle> {
    public OrderTitleAdapter(@NonNull Context context, @NonNull List<OrderTitle> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        TextView itemDdh;
        TextView itemLx;
        TextView itemRq;
        itemDdh = convertView.findViewById(R.id.item_ddh);
        itemLx = convertView.findViewById(R.id.item_lx);
        itemRq = convertView.findViewById(R.id.item_rq);
        OrderTitle orderTitle = getItem(position);
        itemDdh.setText(orderTitle.getId()+"");
        itemLx.setText(orderTitle.getType());
        itemRq.setText(orderTitle.getDate());
        return convertView;
    }

    private void initView() {


    }
}
