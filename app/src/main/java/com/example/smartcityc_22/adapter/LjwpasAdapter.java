package com.example.smartcityc_22.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 15:34
 */
public class LjwpasAdapter extends BaseExpandableListAdapter {
    private List<String> strings;
    private Map<String, List<String>> map;

    public LjwpasAdapter(List<String> strings, Map<String, List<String>> map) {
        this.strings = strings;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return strings.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(strings.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private int image[] = {R.mipmap.khslj, R.mipmap.yhlj, R.mipmap.qtlj, R.mipmap.glj, R.mipmap.slj};

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ljwpzs_ittem_fu, parent, false);

        ImageView itemImage;
        TextView itemName;
        itemImage = convertView.findViewById(R.id.item_image);
        itemName = convertView.findViewById(R.id.item_name);
        itemImage.setImageResource(image[groupPosition]);
        itemName.setText(strings.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ljwpzs_item_zi, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(map.get(strings.get(groupPosition)).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void initView() {


    }
}
