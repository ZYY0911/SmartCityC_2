package com.example.smartcityc_22.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityc_22.R;
import com.example.smartcityc_22.adapter.PhotoAdapter;
import com.example.smartcityc_22.bean.UserInfo;
import com.example.smartcityc_22.util.NetImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 11:34
 */
public class ChooseActivity extends AppCompatActivity {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutInfo;
    private NetImageView ivPhoto;
    private GridView girdPhoto;


    private UserInfo userInfo;
    private Integer images[] = {R.mipmap.user1, R.mipmap.user2,
            R.mipmap.user3, R.mipmap.user4, R.mipmap.user5, R.mipmap.user6, R.mipmap.user7, R.mipmap.user8};

    private List<Integer> integers;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);

        initView();
        userInfo = (UserInfo) getIntent().getSerializableExtra("info");
        ivPhoto.setMyUrl(userInfo.getAvatar());
        integers = new ArrayList<>();
        title.setText("选择头像");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("date", ivPhoto.getTag().toString());
                intent.putExtra("index", index);
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        for (int i = 0; i < images.length; i++) {
            integers.add(images[i]);
        }
        girdPhoto.setAdapter(new PhotoAdapter(this, integers));
        girdPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ivPhoto.setTag("user" + (position + 1) + ".png");
                index = position;
                ivPhoto.setImageResource(integers.get(position));
            }
        });
        ivPhoto.setTag(userInfo.getAvatar().substring(userInfo.getAvatar().lastIndexOf("/")));

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        layoutInfo = findViewById(R.id.layout_info);
        ivPhoto = findViewById(R.id.iv_photo);
        girdPhoto = findViewById(R.id.gird_photo);
    }
}
