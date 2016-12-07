package com.example.chenquan.likebaiduanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by chenquan on 2016/12/6.
 */

public class BaiduProgressBar2 extends View {
    private int sweepindex = 0;
    private int[] colors =new int[]{getResources().getColor(R.color.red),getResources().getColor(R.color.blue),getResources().getColor(R.color.yellow)};
    /**
     * 动画所执行的最大偏移量（即中间点和最左边的距离）
     */
    private Float maxWidth = 200f;

    /**
     * 三个圆的半径
     */
    private Float radius = 30f;

    /**
     * 当前偏移的X坐标
     */
    private Float currentX = 0f;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 属性动画
     */
    private ValueAnimator valueAnimator;

    public BaiduProgressBar2(Context context) {
        super(context);
        startAnimator();
    }

    public BaiduProgressBar2(Context context, AttributeSet attrs) {
        super(context, attrs);
        startAnimator();
    }

    public BaiduProgressBar2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startAnimator();
    }

    public BaiduProgressBar2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        startAnimator();
    }

    private void startAnimator(){
        float[] floats=new float[]{0f,maxWidth,0};
        valueAnimator = ValueAnimator.ofFloat(floats);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentX = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                sweep(sweepindex);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;

        /**画左边的圆**/
        paint.setColor(colors[0]);
        canvas.drawCircle(centerX - currentX, centerY, radius, paint);

        /**画右边的圆**/
        paint.setColor(colors[1]);
        canvas.drawCircle(centerX + currentX, centerY, radius, paint);

        /**画中间的圆**/
        paint.setColor(colors[2]);
        canvas.drawCircle(centerX, centerY, radius, paint);

    }

    /**
     * 每次让先执行动画的目标和中间停止的动画目标交换
     *
     * @param a 最先执行的动画的索引
     */
    private void sweep(int a) {
        int temp = colors[2];
        colors[2] = colors[a];
        colors[a] = temp;

        if (a == 0) {
            sweepindex = 1;
        } else {
            sweepindex = 0;
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }
}
