package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.util.RangeImage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 8:33
 */
public class SubjectAdapter extends ArrayAdapter<String> {

    private int images[] = {R.mipmap.c, R.mipmap.d, R.mipmap.a, R.mipmap.e};

    public SubjectAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_item, parent, false);
        RangeImage itemImage;
        TextView itemName;
        itemImage = convertView.findViewById(R.id.item_image);
        itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(getItem(position));
        itemImage.setImageResource(images[position]);
        return convertView;
    }

    private void initView() {


    }
}
