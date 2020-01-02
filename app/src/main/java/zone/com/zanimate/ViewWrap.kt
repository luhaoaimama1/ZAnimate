package zone.com.zanimate

import android.view.View

import zone.com.zanimate.value.ValueAnimatorProxy
import zone.com.zanimate.value.ValueAnimatorProxyAbstract

/**
 * Created by fuzhipeng on 16/8/23.
 */
class ViewWrap : ValueAnimatorProxyAbstract<ViewWrap>() {
    private var view: View? = null

    init {
        child = this//一定要设置 没设置到时候会抛异常给你;
    }

    override fun initDefaultValueAnimator(): android.animation.ValueAnimator {
        return ValueAnimatorProxy.ofFloat(0f, 1f)
                //                .setRepeatCount(ValueAnimatorProxy.INFINITE)
                .setRepeatMode(android.animation.ValueAnimator.REVERSE)
                .setDuration(1000).source()
    }

    fun setShineView(view: View) {
        this.view = view
    }

    override fun start(): ViewWrap {
        addUpdateListener { animation -> println("ViewWrap测试~继承好使不~" + animation.animatedValue) }
        return super.start()
    }

    override fun addUpdateListener(listener: android.animation.ValueAnimator.AnimatorUpdateListener): ViewWrap {
        return super.addUpdateListener(listener)
    }
}
