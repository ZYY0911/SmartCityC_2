package com.example.smartcityc_22.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.DfAdapter;
import com.example.smartcityc_22.bean.HdDetials;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 17:13
 */
public class SfActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView sfList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.df_layout);

        initView();
        title.setText("水费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVoley_DetaLi();
    }

    List<HdDetials> hdDetials;

    private void setVoley_DetaLi() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("accountByUserId")
                .setJsonObject("userid", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdDetials = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HdDetials>>() {
                                }.getType());
                        for (int i = hdDetials.size() - 1; i >= 0; i--) {
                            HdDetials hoDetail = hdDetials.get(i);
                            if (hoDetail.getType().equals("电费")) {
                                hdDetials.remove(i);
                            }
                        }
                        sfList.setAdapter(new DfAdapter(SfActivity.this, hdDetials));
                        sfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                DFDetailsActivity.newInstance(hdDetials.get(position), SfActivity.this);
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        sfList = findViewById(R.id.sf_list);
    }
}
