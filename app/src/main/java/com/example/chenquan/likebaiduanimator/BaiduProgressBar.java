package com.example.chenquan.likebaiduanimator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenquan on 2016/12/6.
 */

public class BaiduProgressBar extends FrameLayout {

    private int startindex = 0;
    private int[] srcs  = new int[]{R.mipmap.green,R.mipmap.red,R.mipmap.yellow};
    private List<ImageView> imageViews = new ArrayList<>();
    private AnimatorSet animatorSet;
    private int maxRadius = 200;


    public BaiduProgressBar(Context context) {
        super(context);
        init();
    }

    public BaiduProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaiduProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaiduProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    /**
     * ImageView集合初始化
     * */
    private void assignView(){
        ImageView imageViewgreen = (ImageView) findViewById(R.id.green);
        ImageView imageViewred= (ImageView) findViewById(R.id.red);
        ImageView imageViewyellow = (ImageView) findViewById(R.id.yellow);
        imageViews.add(imageViewgreen);
        imageViews.add(imageViewred);
        imageViews.add(imageViewyellow);
    }
    /**
     * 初始化
     * */
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.baiduprogress,this,true);
        assignView();
        startAnimator();

    }
    /**
     * 动画
     * */
    private void startAnimator(){
        /**向左来回移动的X位移动画**/
        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(imageViews.get(0), "translationX", 0, -maxRadius, 0);
        objectAnimatorLeft.setRepeatCount(ObjectAnimator.INFINITE);//无限循环
        objectAnimatorLeft.setDuration(1000);

        /**向右来回移动的X位移动画**/
        ObjectAnimator  objectAnimatorRight = ObjectAnimator.ofFloat(imageViews.get(1), "translationX", 0, maxRadius, 0);
        objectAnimatorRight.setRepeatCount(-1);//无限循环
        objectAnimatorRight.setDuration(1000);

        animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorLeft).with(objectAnimatorRight);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        /**
         * AccelerateDecelerateInterpolator 动画的变化速率开始和结束时慢，中间时快
         * AccelerateInterpolator 开始时比较慢，并且慢慢加速。
         * AnticipateInterpolator 开始时向后甩然后向前滑到
         * AnticipateOvershootInterpolator 开始时向后甩然后向前超过终止时的值最后回退到终止的值。
         * BounceInterpolator 在结束时反弹
         * CycleInterpolator 动画重复特定的次数，变化速率以正弦曲线变化
         * DecelerateInterpolator 以一个极快的速率开始慢慢减速
         * LinearInterpolator 匀速变化
         * OvershootInterpolator 向前甩一定值后再回到原来位置
         * */

        animatorSet.start();

        objectAnimatorLeft.addListener(new Animator.AnimatorListener() {
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
                /**
                 * 开始执行的第一个动画的索引，
                 * 由于第一个和第二个同时当执行，
                 * 当第一遍执行完毕后就让第一个停下来在中间位置，换原来中间位置的第三个开始执行动画，
                 * 以此类推，当第二遍执行完毕后第二个停下来，中间位置的开始执行动画。
                 */
                if (startindex == 0) {
                    sweep(0, 2);
                    startindex = 1;
                } else {
                    sweep(1, 2);
                    startindex = 0;
                }

            }
        });


    }
    /**
     * 每次让先执行动画的目标和中间停止的动画目标交换
     *
     * @param a 最先执行的动画的索引
     * @param b 在中间动画的索引
     *
     */
    private void sweep( int a , int b){
        imageViews.get(a).setImageResource(srcs[b]);
        imageViews.get(b).setImageResource(srcs[a]);
        int temp = srcs[b];
        srcs[b] = srcs[a];
        srcs[a] = temp;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorSet.cancel();
    }
}
