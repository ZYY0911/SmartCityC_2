package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.TccHistory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 9:47
 */
public class TccHistoryAdapter extends ArrayAdapter<TccHistory> {

    private String name;
    private int count;

    public TccHistoryAdapter(@NonNull Context context, @NonNull List<TccHistory> objects, String name, int count) {
        super(context, 0, objects);
        this.name = name;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.tcc_history_item, parent, false);

        TextView itemCp;
        TextView itemJe;
        TextView itemIn;
        TextView itemOut;
        TextView itemName;
        itemCp = convertView.findViewById(R.id.item_cp);
        itemJe = convertView.findViewById(R.id.item_je);
        itemIn = convertView.findViewById(R.id.item_in);
        itemOut = convertView.findViewById(R.id.item_out);
        itemName = convertView.findViewById(R.id.item_name);
        TccHistory tccHistory = getItem(position);
        itemCp.setText(tccHistory.getCarNum());
        itemIn.setText(tccHistory.getInTime());
        itemOut.setText(tccHistory.getOutTime());
        itemJe.setText(tccHistory.getCharge() + "元");
        itemName.setText(name);

        return convertView;
    }

    private void initView() {


    }
}
