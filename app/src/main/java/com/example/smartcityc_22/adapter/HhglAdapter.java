package com.example.smartcityc_22.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.HdDetials;
import com.example.smartcityc_22.bean.HoItem;
import com.example.smartcityc_22.util.MyOnClickItem;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 16:59
 */
public class HhglAdapter extends BaseExpandableListAdapter {

    private List<HoItem> hoItems;

    private Map<HoItem, List<HdDetials>> map;


    public HhglAdapter(List<HoItem> hoItems, Map<HoItem, List<HdDetials>> map) {
        this.hoItems = hoItems;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return hoItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(hoItems.get(groupPosition)).size();
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

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ho_item_fu, parent, false);

        TextView itemType;
        ImageView itemLeft;
        itemType = convertView.findViewById(R.id.item_type);
        itemLeft = convertView.findViewById(R.id.item_left);
        itemLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickItem.myClick(groupPosition, "");

            }
        });
        itemType.setText(hoItems.get(groupPosition).getGroupName());
        return convertView;
    }

    private MyOnClickItem myOnClickItem;

    public void setMyOnClickItem(MyOnClickItem myOnClickItem) {
        this.myOnClickItem = myOnClickItem;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ho_item_zi, parent, false);
        TextView itemType;
        TextView itemNumZi;
        itemType = convertView.findViewById(R.id.item_type);
        itemNumZi = convertView.findViewById(R.id.item_num_zi);
        HdDetials details = map.get(hoItems.get(groupPosition)).get(childPosition);
        itemNumZi.setText(details.getAccountId() + "|" + details.getAccountAddress());
        itemType.setText(details.getType());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private void initView() {


    }
}
