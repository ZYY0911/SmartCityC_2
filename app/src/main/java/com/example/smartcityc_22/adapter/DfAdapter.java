package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.HdDetials;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 17:16
 */
public class DfAdapter extends ArrayAdapter<HdDetials> {

    public DfAdapter(@NonNull Context context, @NonNull List<HdDetials> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ho_item_zi, parent, false);
        TextView itemType = convertView.findViewById(R.id.item_type);
        TextView itemNumZi = convertView.findViewById(R.id.item_num_zi);
        HdDetials details = getItem(position);
        itemNumZi.setText(details.getAccountId() + "|" + details.getAccountAddress());
        itemType.setText(details.getType());
        return convertView;
    }

    private void initView() {


    }
}
