package zone.com.zanimate.object.plugins;

import com.nineoldandroids.animation.AnimatorSet;

import zone.com.zanimate.object.ObjectAnimatorHelper;

/**
 * Created by sxl on 2016/6/27.
 */
public class ExampleAnimator implements ObjectAnimatorHelper.BaseViewAnimator {
    @Override
    public void prepare(AnimatorSet mAnimatorSet) {
        mAnimatorSet.playSequentially(
                ObjectAnimatorHelper.ofFloat("scaleX",1,0.9f,0.9f,1.1f,1.1f,1.1f,1.1f,1.1f,1.1f,1),
                ObjectAnimatorHelper.ofFloat("scaleY",1,0.9f,0.9f,1.1f,1.1f,1.1f,1.1f,1.1f,1.1f,1),
                ObjectAnimatorHelper.ofFloat("rotation",0 ,-3 , -3, 3, -3, 3, -3,3,-3,0)
        );
        mAnimatorSet.setDuration(1200);
    }
}
