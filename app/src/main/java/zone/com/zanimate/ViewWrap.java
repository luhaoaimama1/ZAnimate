package zone.com.zanimate;
import android.view.View;

import zone.com.zanimate.value.ValueAnimatorProxy;
import zone.com.zanimate.value.ValueAnimatorProxyAbstract;

/**
 * Created by fuzhipeng on 16/8/23.
 */
public class ViewWrap extends ValueAnimatorProxyAbstract<ViewWrap> {
    private View view;

    public ViewWrap() {
        child = this;//一定要设置 没设置到时候会抛异常给你;
    }

    @Override
    public com.nineoldandroids.animation.ValueAnimator initDefaultValueAnimator() {
        return ValueAnimatorProxy.ofFloat(0F, 1F)
//                .setRepeatCount(ValueAnimatorProxy.INFINITE)
                .setRepeatMode(com.nineoldandroids.animation.ValueAnimator.REVERSE)
                .setDuration(1000).source();
    }

    public void setShineView(View view) {
        this.view = view;
    }

    @Override
    public ViewWrap start() {
        addUpdateListener(new com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(com.nineoldandroids.animation.ValueAnimator animation) {
                System.out.println("ViewWrap测试~继承好使不~"+animation.getAnimatedValue());
            }
        });
        return super.start();
    }

    @Override
    public ViewWrap addUpdateListener(com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener listener) {
        return super.addUpdateListener(listener);
    }
}
