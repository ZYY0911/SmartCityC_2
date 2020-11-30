package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.OrderDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 14:40
 */
public class OrderDetialsAdapter extends ArrayAdapter<OrderDetails> {

    public OrderDetialsAdapter(@NonNull Context context, @NonNull List<OrderDetails> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.orde1r_detials_item, parent, false);
        TextView itemName;
        TextView itemSp;
        TextView itemDj;
        TextView itemSl;
        itemName = convertView.findViewById(R.id.item_name);
        itemSp = convertView.findViewById(R.id.item_sp);
        itemDj = convertView.findViewById(R.id.item_dj);
        itemSl = convertView.findViewById(R.id.item_sl);
        OrderDetails orderDetails = getItem(position);
        itemName.setText(orderDetails.getBusiness());
        itemSp.setText(orderDetails.getCommodity());
        itemDj.setText(orderDetails.getPrice()+"");
        itemSl.setText(orderDetails.getCount());
        return convertView;
    }

    private void initView() {


    }
}
