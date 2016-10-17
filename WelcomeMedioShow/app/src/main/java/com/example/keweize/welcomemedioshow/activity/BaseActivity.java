package com.example.keweize.welcomemedioshow.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kwz on 2016/3/7.
 * 所有的Activity都必须继承这个类，实现以下三个抽象方法
 * initVariables()  初始化数据变量
 * initViews(saveDInstanceState)  初始化视图变量
 * loadData();  加载数据
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    /**
    * 初始化变量
    * */
    protected abstract void initVariables();

    /**
    * 初始化控件
    * */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
    * 加载动态数据（如网络接口拿下来的数据）
    * */
    protected abstract void loadData();
}
