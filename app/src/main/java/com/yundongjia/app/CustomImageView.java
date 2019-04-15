package com.yundongjia.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 自定义imageview 方向盘
 * @author
 */
public class CustomImageView extends AppCompatImageView {

    private static final String TAG = "CustomImageView";
    private int y_down;
    private int x_down;
    private int centerX;
    private float angle;
    private int centerY;
    private Bitmap mBitmap;
    private Paint mPaint;

    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.control);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBitmap.getWidth(),mBitmap.getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 触摸点
                x_down = (int) event.getX();
                y_down = (int) event.getY();
                Log.d(TAG, "ACTION_DOWN: x " + x_down + "  y " + y_down);

                centerX = getWidth() / 2;
                centerY = getHeight()/ 2;
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                Log.d(TAG, "onTouch: x " + x + "  y " + y);
                if(x_down == x && y_down == y ){
                    return true ;
                }
                angle = angle(new Point(centerX, centerY), new Point(x_down, y_down), new Point(x, y));
                Log.d(TAG, "onTouch: angle" + angle);
                if (!Double.isNaN(angle)){
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                 angle = 0;
                 postInvalidate();
                break;
            default:
        }
        return true;
    }

    /**
     * 计算旋转度数
     * @param cen
     * @param first
     * @param second
     * @return
     */
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
        // 异常处理，因为算出来会有误差绝对值可能会超过一，所以需要处理一下
        if (cosDegree > 1) {
            cosDegree = 1;
        } else if (cosDegree < -1) {
            cosDegree = -1;
        }

        // 计算弧度
        double radian = Math.acos(cosDegree);
        // 计算旋转过的角度，顺时针为正，逆时针为负
        return (float) (isClockwise ? Math.toDegrees(radian) : -Math.toDegrees(radian));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(angle > 90){
            angle = 90;
        }else if(angle < 0 && Math.abs(angle) > 90){
            angle = -90;
        }
        canvas.rotate(angle, getWidth()/2 , getHeight()/2);
        canvas.drawBitmap(mBitmap ,0,0 ,mPaint);
    }

}
