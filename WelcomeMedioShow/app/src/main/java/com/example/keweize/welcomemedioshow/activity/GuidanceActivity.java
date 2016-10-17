package com.example.keweize.welcomemedioshow.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.keweize.welcomemedioshow.R;
import com.example.keweize.welcomemedioshow.adapter.GuidanceAdapter;
import com.example.keweize.welcomemedioshow.view.CircleIndicator;

import java.io.File;

public class GuidanceActivity extends BaseActivity {

    private VideoView videoView;
    private ImageView imgEntry;
    private ViewPager viewPager;
    private GuidanceAdapter adapter;
    private CircleIndicator circleIndicator;
    //注入adapter中的数组
    private String[] data = {"欢迎来到玩艺网,一款设计师品牌分享品质生活和开心送礼的购物应用",
            "定格生活中最让你心动的瞬间,我们让他全新呈现", "你送的礼物,给了他一个终身难忘的欢乐",
            "加入LuckyBox,每月寻找一款属于你的小幸运", "邀请你志同道合的小伙伴下载玩艺网,你可获得现金赠送"};

    String videoPath;


    @Override
    protected void initVariables() {
        videoPath = getIntent().getStringExtra("VideoPath");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guidance);
        videoView = (VideoView) this.findViewById(R.id.id_video);
        imgEntry = (ImageView) this.findViewById(R.id.id_imgEntry);
        imgEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuidanceActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager = (ViewPager) this.findViewById(R.id.id_viewPager);
        adapter = new GuidanceAdapter(getApplicationContext(), data);
        viewPager.setAdapter(adapter);

        circleIndicator = (CircleIndicator) this.findViewById(R.id.id_circleIndicator);
        circleIndicator.setViewPager(viewPager);

    }

    @Override
    protected void loadData() {
        File file = new File(videoPath);
        if (!file.exists()){
            Log.i("MainActivity0", "视频文件不存在");
        }else {
            videoView.setVideoPath(file.getPath());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    //设置为填充父窗体
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            //设置布局
            videoView.setLayoutParams(layoutParams);
            //循环播放
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            videoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null){
            //释放掉占用的内存
            videoView.suspend();
        }
    }
}
