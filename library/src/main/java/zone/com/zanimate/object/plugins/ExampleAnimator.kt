package zone.com.zanimate.`object`.plugins

import android.animation.AnimatorSet

import zone.com.zanimate.`object`.ObjectAnimatorHelper

/**
 * Created by sxl on 2016/6/27.
 */
class ExampleAnimator : ObjectAnimatorHelper.BaseViewAnimator {
    override fun prepare(mAnimatorSet: AnimatorSet) {
        mAnimatorSet.playSequentially(
                ObjectAnimatorHelper.ofFloat("scaleX", 1F, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1F),
                ObjectAnimatorHelper.ofFloat("scaleY", 1F, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1F),
                ObjectAnimatorHelper.ofFloat("rotation", 0F, -3F, -3F, 3F, -3F, 3F, -3F, 3F, -3F, 0F)
        )
        mAnimatorSet.duration = 1200
    }
}
