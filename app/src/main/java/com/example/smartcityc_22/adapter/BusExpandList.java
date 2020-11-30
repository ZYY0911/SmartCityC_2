package com.example.smartcityc_22.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.BusStation;
import com.example.smartcityc_22.bean.BusTitle;
import com.example.smartcityc_22.util.MyOnClickItem;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 10:09
 */
public class BusExpandList extends BaseExpandableListAdapter {
    private List<BusTitle> busTitles;
    private Map<BusTitle, List<BusStation>> map;

    public BusExpandList(List<BusTitle> busTitles, Map<BusTitle, List<BusStation>> map) {
        this.busTitles = busTitles;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return busTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_fu, parent, false);

        TextView itemName;
        TextView itemLine;
        TextView itemMsg;
        TextView itemStart;
        TextView itemEnd;
        ImageView itemIv;
        itemName = convertView.findViewById(R.id.item_name);
        itemLine = convertView.findViewById(R.id.item_line);
        itemMsg = convertView.findViewById(R.id.item_msg);
        itemStart = convertView.findViewById(R.id.item_start);
        itemEnd = convertView.findViewById(R.id.item_end);
        itemIv = convertView.findViewById(R.id.item_iv);
        BusTitle busTitle = busTitles.get(groupPosition);
        itemName.setText(busTitle.getPathName());
        itemLine.setText(busTitle.getStartSite() + "-" + busTitle.getEndSite());
        if (isExpanded) {
            itemIv.setImageResource(R.mipmap.xiajiantou);
        } else {
            itemIv.setImageResource(R.mipmap.youjiantou);
        }
        itemStart.setText(busTitle.getRunTime1());
        itemEnd.setText(busTitle.getRunTime2());
        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.myClick(groupPosition, "");
            }
        });
        itemMsg.setText("票价：" + busTitle.getPrice() + ".00    里程：" + busTitle.getMileage());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_zi, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        List<BusStation> busStations = map.get(busTitles.get(groupPosition));
        String str = "";
        for (int i = 0; i < busStations.size(); i++) {
            if (i == 0) {
                str = busStations.get(i).getSiteName();
            } else {
                str += "\r\n" + busStations.get(i).getSiteName();
            }
        }
        itemName.setText(str);
        return convertView;
    }

    private MyOnClickItem onClickItem;

    public void setOnClickItem(MyOnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void initView() {
    }
}
