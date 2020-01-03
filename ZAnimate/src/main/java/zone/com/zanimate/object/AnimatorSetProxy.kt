package zone.com.zanimate.`object`

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.animation.Interpolator

import java.util.ArrayList


/**
 * Created by fuzhipeng on 16/6/29.
 */
class AnimatorSetProxy(internal var mAnimatorSet: AnimatorSet) : Cloneable {

    val childAnimations: ArrayList<Animator>
        get() = mAnimatorSet.childAnimations

    val isRunning: Boolean
        get() = mAnimatorSet.isRunning

    val isStarted: Boolean
        get() = mAnimatorSet.isStarted

    val startDelay: Long
        get() = mAnimatorSet.startDelay

    val duration: Long
        get() = mAnimatorSet.duration

    val listeners: ArrayList<Animator.AnimatorListener>
        get() = mAnimatorSet.listeners


    fun playTogether(vararg items: Animator): AnimatorSetProxy {
        mAnimatorSet.playTogether(*items)
        return this
    }

    fun playTogether(items: Collection<Animator>): AnimatorSetProxy {
        mAnimatorSet.playTogether(items)
        return this
    }

    fun playSequentially(vararg items: Animator): AnimatorSetProxy {
        mAnimatorSet.playSequentially(*items)
        return this
    }

    fun playSequentially(items: List<Animator>): AnimatorSetProxy {
        mAnimatorSet.playSequentially(items)
        return this
    }


    fun cancel(): AnimatorSetProxy {
        mAnimatorSet.cancel()
        return this
    }

    fun end(): AnimatorSetProxy {
        mAnimatorSet.end()
        return this
    }

    public override fun clone(): AnimatorSetProxy {
        return AnimatorSetProxy(mAnimatorSet.clone())
    }

    fun addListener(listener: Animator.AnimatorListener): AnimatorSetProxy {
        mAnimatorSet.addListener(listener)
        return this
    }

    fun removeListener(listener: Animator.AnimatorListener): AnimatorSetProxy {
        mAnimatorSet.removeListener(listener)
        return this
    }

    fun removeAllListeners(): AnimatorSetProxy {
        mAnimatorSet.removeAllListeners()
        return this
    }


    //正常是11对应的关系;
    //特殊情况:但是如果不等 并且动画数量大于objs的时候,最后一个objs应用到剩下没有被对应的动画;
    fun setTarget(vararg objs: Any): AnimatorSetProxy? {
        if (objs.isEmpty())
            return null
        val childs = mAnimatorSet.childAnimations
        for (i in childs.indices) {
            val length = objs.size
            if (i < length)
                childs[i].setTarget(objs[i])
            else
                childs[i].setTarget(objs[length - 1])
        }
        return this
    }

    //等比设置 比如8个动画 3个objs,那么123 对应obj1 456对应obj2 78对应obj3
    fun setTargetRatio(vararg objs: Any): AnimatorSetProxy? {
        if (objs.isEmpty())
            return null
        val childs = mAnimatorSet.childAnimations
        for (i in childs.indices)
            childs[i].setTarget(objs[i / objs.size])
        return this
    }

    fun setInterpolator(/*Time*/interpolator: Interpolator): AnimatorSetProxy {
        mAnimatorSet.interpolator = interpolator
        return this
    }

    fun setStartDelay(setStartDelay: Long): AnimatorSetProxy {
        mAnimatorSet.startDelay = setStartDelay
        return this
    }

    fun setupEndValues(): AnimatorSetProxy {
        mAnimatorSet.setupEndValues()
        return this
    }

    fun setupStartValues(): AnimatorSetProxy {
        mAnimatorSet.setupStartValues()
        return this
    }

    //如果这个设置了 那么所有的 动画全被被迭代设置; 可以进去看看
    fun setDuration(duration: Long): AnimatorSetProxy {
        mAnimatorSet.duration = duration
        return this
    }

    fun start(): AnimatorSetProxy {
        mAnimatorSet.start()
        return this
    }

    fun source(): AnimatorSet {
        return mAnimatorSet
    }

}
