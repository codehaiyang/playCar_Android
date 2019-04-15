package com.yundongjia.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * 首页
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mIbEasy;
    private ImageButton mIbFunction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        mIbEasy = findViewById(R.id.ib_easy);
        mIbFunction = findViewById(R.id.ib_function);

        mIbEasy.setOnClickListener(this);
        mIbFunction.setOnClickListener(this);
    }

    /**
     * 点击事件处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        switch (view.getId()){
            // 简易版
            case R.id.ib_easy:
                intent.putExtra("type","0");
                startActivity(intent);
                break;
            // 功能版
            case R.id.ib_function:
                intent.putExtra("type","1");
                startActivity(intent);
                break;
                default:
        }
    }
}
