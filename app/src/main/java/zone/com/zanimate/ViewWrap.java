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
    public android.animation.ValueAnimator initDefaultValueAnimator() {
        return ValueAnimatorProxy.ofFloat(0F, 1F)
//                .setRepeatCount(ValueAnimatorProxy.INFINITE)
                .setRepeatMode(android.animation.ValueAnimator.REVERSE)
                .setDuration(1000).source();
    }

    public void setShineView(View view) {
        this.view = view;
    }

    @Override
    public ViewWrap start() {
        addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(android.animation.ValueAnimator animation) {
                System.out.println("ViewWrap测试~继承好使不~"+animation.getAnimatedValue());
            }
        });
        return super.start();
    }

    @Override
    public ViewWrap addUpdateListener(android.animation.ValueAnimator.AnimatorUpdateListener listener) {
        return super.addUpdateListener(listener);
    }
}
