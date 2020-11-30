package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.TccInfo;
import com.example.smartcityc_22.util.MyOnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 9:20
 */
public class TccAdapter extends ArrayAdapter<TccInfo> {

    public TccAdapter(@NonNull Context context, @NonNull List<TccInfo> objects, int count) {
        super(context, 0, objects);
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    @Override
    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.tcc_item, parent, false);


        TextView itemName;
        TextView itemAddress;
        TextView itemJl;
        TextView itemKwsl;
        TextView itemSfl;
        itemName = convertView.findViewById(R.id.item_name);
        itemAddress = convertView.findViewById(R.id.item_address);
        itemJl = convertView.findViewById(R.id.item_jl);
        itemKwsl = convertView.findViewById(R.id.item_kwsl);
        itemSfl = convertView.findViewById(R.id.item_sfl);
        TccInfo tccInfo = getItem(position);
        itemName.setText(tccInfo.getParkName());
        itemAddress.setText(tccInfo.getAddress());
        itemJl.setText("距离:" + tccInfo.getDistance());
        itemSfl.setText("收费率:" + tccInfo.getRate());
        itemKwsl.setText("空位数量：" + tccInfo.getSurCarPort());
        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickItem.myClick(position, "1");
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickItem.myClick(position, "2");
            }
        });
        return convertView;
    }

    private MyOnClickItem myOnClickItem;

    public void setMyOnClickItem(MyOnClickItem myOnClickItem) {
        this.myOnClickItem = myOnClickItem;
    }

    private void initView() {


    }
}
