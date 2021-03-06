package com.yundongjia.app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ImageButton mIbGearOne;
    private ImageButton mIbGearTwo;
    private ImageButton mIbGearThree;
    private ImageButton mIbReturn;
    private ImageButton mIbGo;
    private ImageButton mIbBack;
    private ImageButton mIbStop;
    private ImageButton mIbSetting;
    private ImageButton mIbVoice;
    private ImageButton mIbLamp;
    private ImageButton mIbMusic;

    private boolean isShow = false;
    private ImageView mIbDirection;
    private int centerX;
    private int centerY;

    private boolean lamp_type = true;
    private boolean music_type = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        findViewById(R.id.ib_setting);
        mIbGearOne = findViewById(R.id.ib_gear_one);
        mIbGearTwo = findViewById(R.id.ib_gear_two);
        mIbGearThree = findViewById(R.id.ib_gear_three);
        mIbGo = findViewById(R.id.ib_go);
        mIbBack = findViewById(R.id.ib_back);
        mIbStop = findViewById(R.id.ib_stop);
        mIbReturn = findViewById(R.id.ib_return);
        mIbSetting = findViewById(R.id.ib_setting);
        mIbVoice = findViewById(R.id.ib_voice);
        mIbLamp = findViewById(R.id.ib_lamp);
        mIbMusic = findViewById(R.id.ib_music);
        mIbDirection = findViewById(R.id.iv_direction);

        // 版本类型
        String type = getIntent().getStringExtra("type");
        if("0".equals(type)){
            mIbGearOne.setVisibility(View.GONE);
            mIbGearTwo.setVisibility(View.GONE);
            mIbGearThree.setVisibility(View.GONE);
        }else{
            mIbGearOne.setVisibility(View.VISIBLE);
            mIbGearTwo.setVisibility(View.VISIBLE);
            mIbGearThree.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 设置监听事件
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setListener() {
        mIbSetting.setOnClickListener(this);
        mIbGearOne.setOnClickListener(this);
        mIbGearTwo.setOnClickListener(this);
        mIbGearThree.setOnClickListener(this);
        mIbReturn.setOnClickListener(this);
        mIbLamp.setOnClickListener(this);
        mIbMusic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_gear_one:
                break;
            case R.id.ib_gear_two:
                break;
            case R.id.ib_gear_three:
                break;
            case R.id.ib_go:
                break;
            case R.id.ib_back:
                break;
            case R.id.ib_stop:
                break;
            case R.id.ib_setting:
                if(!isShow){
                    isShow = true;
                    animationShow(mIbVoice,-180,10);
                    animationShow(mIbLamp,-100,100);
                    animationShow(mIbMusic,0,180);
                }else {
                    isShow = false;
                    animationHint(mIbVoice,180,-10);
                    animationHint(mIbLamp,100,-100);
                    animationHint(mIbMusic,0,-180);
                }
                break;
            case R.id.ib_return:
                finish();
                break;
            case R.id.ib_lamp:
                lamp_type = !lamp_type;
                if(lamp_type){
                    mIbLamp.setBackgroundResource(R.mipmap.icon_lamp);
                }else {
                    mIbLamp.setBackgroundResource(R.mipmap.icon_lamp_close);
                }
                break;
            case R.id.ib_music:
                music_type = !music_type;
                if (music_type){
                    mIbMusic.setBackgroundResource(R.mipmap.icon_music);
                }else {
                    mIbMusic.setBackgroundResource(R.mipmap.icon_music_close);
                }
                break;
                default:
        }
    }

    /**
     * 设置按钮显示动画
     * @param view
     * @param x
     * @param y
     */
    private void animationShow(View view, int x, int y) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, x),
                ObjectAnimator.ofFloat(view, "translationY", 0, y),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f));
        set.start();
    }

    /**
     * 设置按钮隐藏动画
     * @param view
     * @param x
     * @param y
     */
    private void animationHint(View view,int x,int y) {
         if (view != null) {
             view.setVisibility(View.GONE);
         }
         AnimatorSet set = new AnimatorSet();
         set.playTogether(
                 ObjectAnimator.ofFloat(view, "translationX", x, 0),
                 ObjectAnimator.ofFloat(view, "translationY", y, 0),
                 ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                 ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                 ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
         set.start();

     }
}
