package zone.com.zanimate;

import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

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
    public ValueAnimator initDefaultValueAnimator() {
        return ValueAnimatorProxy.ofFloat(0F, 1F)
//                .setRepeatCount(ValueAnimator.INFINITE)
                .setRepeatMode(ValueAnimator.REVERSE)
                .setDuration(1000).source();
    }

    public void setShineView(View view) {
        this.view = view;
    }

    @Override
    public ViewWrap start() {
        addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("继承好使不~"+animation.getAnimatedValue());
            }
        });
        return super.start();
    }

    @Override
    public ViewWrap addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        return super.addUpdateListener(listener);
    }
}
