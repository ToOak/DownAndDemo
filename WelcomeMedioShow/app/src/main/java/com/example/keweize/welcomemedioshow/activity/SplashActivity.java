package com.example.keweize.welcomemedioshow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import com.example.keweize.welcomemedioshow.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashActivity extends BaseActivity {

    String APP_PATH;
    String VIDEO_NAME = "welcome_media.mp4";

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        APP_PATH = getApplicationContext().getFilesDir().getAbsolutePath();
    }

    @Override
    protected void loadData() {

        //初始化welcome_media.mp4文件。如果内存卡存在则直接播放，如果不存在则从资源文件中读取写入内存卡
        if (!new File(APP_PATH + VIDEO_NAME).exists()){
            try {
                //输入流
                InputStream in = getApplicationContext().getResources().openRawResource(R.raw.welcome_media);
                //输出流
                OutputStream out = new FileOutputStream(APP_PATH + VIDEO_NAME);
                //将资源文件welcome_media.mp3写入到sd卡
                byte[] buffer = new byte[1024];
                int length;

                while ((length = in.read(buffer)) > 0){
                    out.write(buffer, 0, length);
                }
                out.flush();       //刷新
                out.close();        //关闭
                in.close();

                Log.i("SplashActivity", "mp4写入成功");

            } catch (Exception  e) {
                e.printStackTrace();
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                File file = new File(APP_PATH + VIDEO_NAME);
                if (file.exists()){
                    //如果视频写入成功，则打开引导页面
                    Intent intent = new Intent(SplashActivity.this, GuidanceActivity.class);
                    intent.putExtra("VideoPath", APP_PATH + VIDEO_NAME);
                    startActivity(intent);
                }else {
                    //如果视频写入不成功，则跳过引导页，直接打开程序主界面
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }
}
