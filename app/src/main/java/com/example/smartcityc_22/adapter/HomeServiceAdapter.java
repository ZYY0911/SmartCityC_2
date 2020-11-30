package com.example.smartcityc_22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.bean.ServiceInfo;
import com.example.smartcityc_22.bean.ServiceOrder;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.CircleImage;
import com.example.smartcityc_22.util.MyOnClickItem;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 8:30
 */
public class HomeServiceAdapter extends ArrayAdapter<ServiceOrder> {

    public HomeServiceAdapter(@NonNull Context context, @NonNull List<ServiceOrder> objects) {
        super(context, 0, objects);
    }


    @Override
    public int getCount() {
        return 10;
    }


    private MyOnClickItem onClickItem;

    public void setOnClickItem(MyOnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
        final CircleImage itemImage;
        final TextView itemName;
        itemImage = convertView.findViewById(R.id.item_image);
        itemName = convertView.findViewById(R.id.item_name);
        if (position == 9) {
            itemName.setText("更多服务");
            itemImage.setImageResource(R.mipmap.more_service);
        } else {
            ServiceOrder serviceOrder = getItem(position);
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("service_info")
                    .setJsonObject("serviceid", serviceOrder.getId())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            ServiceInfo serviceInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                    , ServiceInfo.class);
                            itemName.setText(serviceInfo.getServiceName());
                            itemImage.setMyUrl(serviceInfo.getIcon());
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.myClick(position, itemName.getText().toString());
            }
        });
        return convertView;
    }

    private void initView() {


    }
}
