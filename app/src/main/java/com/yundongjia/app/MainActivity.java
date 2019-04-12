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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化页面
     */
    @SuppressLint("ClickableViewAccessibility")
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

        mIbSetting.setOnClickListener(this);
        mIbGearOne.setOnClickListener(this);
        mIbGearTwo.setOnClickListener(this);
        mIbGearThree.setOnClickListener(this);
        mIbReturn.setOnClickListener(this);

        mIbDirection.setOnTouchListener(new View.OnTouchListener() {
            private float angle;
            //初始的旋转角度
            private float oriRotation = 0;
            private int y_down;
            private int x_down;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        // 触摸点
                        x_down = (int) event.getX();
                        y_down = (int) event.getY();

                        centerX = view.getWidth() / 2;
                        centerY = view.getHeight() / 2;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        angle = angle(new Point(centerX, centerY), new Point(x_down, y_down), new Point(x, y));
                        Log.d(TAG, "onTouch: angle" + angle);
                        view.setRotation(angle);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setRotation(0f);
                        break;
                        default:
                }
                return true;
            }
        });
    }

    public float angle(Point cen, Point first, Point second) {
        float dx1, dx2, dy1, dy2;

        dx1 = first.x - cen.x;
        dy1 = first.y - cen.y;
        dx2 = second.x - cen.x;
        dy2 = second.y - cen.y;

        // 计算三边的平方
        float ab2 = (second.x - first.x) * (second.x - first.x) + (second.y - first.y) * (second.y - first.y);
        float oa2 = dx1*dx1 + dy1*dy1;
        float ob2 = dx2 * dx2 + dy2 *dy2;

        // 根据两向量的叉乘来判断顺逆时针
        boolean isClockwise = ((first.x - cen.x) * (second.y - cen.y) - (first.y - cen.y) * (second.x - cen.x)) > 0;

        // 根据余弦定理计算旋转角的余弦值
        double cosDegree = (oa2 + ob2 - ab2) / (2 * Math.sqrt(oa2) * Math.sqrt(ob2));
        Log.d(TAG, "cosDegree: " + cosDegree);
        // 异常处理，因为算出来会有误差绝对值可能会超过一，所以需要处理一下
//        if (cosDegree > 1) {
//            cosDegree = 1;
//        } else if (cosDegree < -1) {
//            cosDegree = -1;
//        }

        // 计算弧度
        double radian = Math.acos(cosDegree);
        // 计算旋转过的角度，顺时针为正，逆时针为负
        return (float) (isClockwise ? Math.toDegrees(radian) : -Math.toDegrees(radian));

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
