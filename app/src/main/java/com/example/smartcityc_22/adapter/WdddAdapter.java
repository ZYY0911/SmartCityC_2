package com.example.smartcityc_22.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.Wddd;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;

import org.json.JSONObject;

import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 11:10
 */
public class WdddAdapter extends BaseExpandableListAdapter {
    private List<Wddd> wddds;

    public WdddAdapter(List<Wddd> wddds) {
        this.wddds = wddds;
    }

    @Override
    public int getGroupCount() {
        return wddds.size();
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wddd_item_fu, parent, false);

        TextView itemNum;
        final TextView itemName;
        final TextView itemLine;
        final TextView itemMsg;
        ImageView itemIv;
        itemNum = convertView.findViewById(R.id.item_num);
        itemName = convertView.findViewById(R.id.item_name);
        itemLine = convertView.findViewById(R.id.item_line);
        itemMsg = convertView.findViewById(R.id.item_msg);
        itemIv = convertView.findViewById(R.id.item_iv);
        Wddd wddd = wddds.get(groupPosition);
        itemNum.setText("订单编号：" + wddd.getId());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busListById")
                .setJsonObject("busid", wddd.getBusid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONObject jsonObject1 = jsonObject.optJSONArray(Util.Row).optJSONObject(0);
                        itemName.setText(jsonObject1.optString("pathName"));
                        itemLine.setText(jsonObject1.optString("startSite") + "-" + jsonObject1.optString("endSite"));
                        itemMsg.setText("票价：" + jsonObject1.optString("price"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        if (isExpanded) {
            itemIv.setImageResource(R.mipmap.xiajiantou);
        } else {
            itemIv.setImageResource(R.mipmap.youjiantou);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wddd_item_zi, parent, false);

        TextView itemName = convertView.findViewById(R.id.item_name);
        Wddd wddd = wddds.get(groupPosition);
        itemName.setText(wddd.getTravetime().replace(",", "\r\n"));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private void initView() {
    }
}
