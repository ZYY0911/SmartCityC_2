package com.example.smartcityc_22.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.VolleyError;
import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.TccHistoryAdapter;
import com.example.smartcityc_22.bean.TccHistory;
import com.example.smartcityc_22.bean.TccInfo;
import com.example.smartcityc_22.net.VolleyLo;
import com.example.smartcityc_22.net.VolleyTo;
import com.example.smartcityc_22.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 9:39
 */
public class TccHistoryActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutStart;
    private TextView etStart;
    private LinearLayout layoutEnd;
    private TextView etEnd;
    private Button btQuery;
    private ListView listViewTcc;
    private TextView tvMore;

    public static void newInstance(TccInfo msg, Context context) {
        Intent intent = new Intent(context, TccHistoryActivity.class);
        intent.putExtra("info", msg);
        context.startActivity(intent);
    }

    TccInfo tccInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc_history_layout);
        initView();
        tccInfo = (TccInfo) getIntent().getSerializableExtra("info");
        title.setText("停车场记录");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tccHistoryAdapter.setCount(tccHistoryList.size());
                tccHistoryAdapter.notifyDataSetChanged();
            }
        });
        layoutStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose(etStart);
            }
        });
        layoutEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose(etEnd);
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inTime = etStart.getText().toString();
                String outTime = etEnd.getText().toString();
                VolleyTo volleyTo = new VolleyTo();
                if (inTime.equals("")) {
                    //{parkingid:1,"outTime1":"","outTime2":""}
                    volleyTo.setUrl("getParkingHistoryByIdAndOutTime")
                            .setJsonObject("parkingid", tccInfo.getParkingid())
                            .setJsonObject("outTime1", outTime)
                            .setJsonObject("outTime2", "")
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    tccHistoryList = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                            , new TypeToken<List<TccHistory>>() {
                                            }.getType());
                                    tccHistoryAdapter = new TccHistoryAdapter(TccHistoryActivity.this
                                            , tccHistoryList, tccInfo.getParkName(), tccHistoryList.size());

                                    listViewTcc.setAdapter(tccHistoryAdapter);
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }).start();
                } else if (outTime.equals("")) {
                    volleyTo.setUrl("getParkingHistoryByIdAndInTime")
                            .setJsonObject("parkingid", tccInfo.getParkingid())
                            .setJsonObject("inTime1", inTime)
                            .setJsonObject("inTime1", "")
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    tccHistoryList = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                            , new TypeToken<List<TccHistory>>() {
                                            }.getType());
                                    tccHistoryAdapter = new TccHistoryAdapter(TccHistoryActivity.this
                                            , tccHistoryList, tccInfo.getParkName(), tccHistoryList.size());

                                    listViewTcc.setAdapter(tccHistoryAdapter);
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }).start();
                } else if (outTime.equals("") && inTime.equals("")) {
                    setVolley();
                } else {
                    volleyTo.setUrl("getParkingHistoryByIdAndInTime")
                            .setJsonObject("parkingid", tccInfo.getParkingid())
                            .setJsonObject("inTime1", inTime)
                            .setJsonObject("inTime1", outTime)
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    tccHistoryList = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                            , new TypeToken<List<TccHistory>>() {
                                            }.getType());
                                    tccHistoryAdapter = new TccHistoryAdapter(TccHistoryActivity.this
                                            , tccHistoryList, tccInfo.getParkName(), tccHistoryList.size());

                                    listViewTcc.setAdapter(tccHistoryAdapter);
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }).start();
                }
            }
        });
    }

    private void showChoose(final TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(TccHistoryActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute + ":00");
                    }
                }, 9, 14, true);
                timePickerDialog.show();
            }
        }, 2020, 9, 27);
        datePickerDialog.show();


    }


    private TccHistoryAdapter tccHistoryAdapter;

    Random random = new Random();
    List<TccHistory> tccHistoryList;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getParkingHistoryById")
                .setJsonObject("parkingid", tccInfo.getParkingid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tccHistoryList = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<TccHistory>>() {
                                }.getType());
                        int count = random.nextInt(1);
                        count = 5 + count;
                        if (count > tccHistoryList.size()) {
                            count = tccHistoryList.size();
                        }
                        tccHistoryAdapter = new TccHistoryAdapter(TccHistoryActivity.this
                                , tccHistoryList, tccInfo.getParkName(), count);

                        listViewTcc.setAdapter(tccHistoryAdapter);
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
        layoutStart = findViewById(R.id.layout_start);
        etStart = findViewById(R.id.et_start);
        layoutEnd = findViewById(R.id.layout_end);
        etEnd = findViewById(R.id.et_end);
        btQuery = findViewById(R.id.bt_query);
        listViewTcc = findViewById(R.id.list_view_tcc);
        tvMore = findViewById(R.id.tv_more);
    }
}
