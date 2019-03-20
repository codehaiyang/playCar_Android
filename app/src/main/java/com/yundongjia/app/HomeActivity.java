package com.yundongjia.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mIbEasy;
    private ImageButton mIbFunction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mIbEasy = findViewById(R.id.ib_easy);
        mIbFunction = findViewById(R.id.ib_function);

        mIbFunction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_easy:
                break;
            case R.id.ib_function:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
                default:
        }
    }
}
