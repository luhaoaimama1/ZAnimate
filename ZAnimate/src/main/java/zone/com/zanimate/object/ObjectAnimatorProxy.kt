package zone.com.zanimate.`object`

import android.animation.TimeInterpolator
import android.util.Property
import android.view.animation.Interpolator
import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.animation.ValueAnimator

import java.util.ArrayList

/**
 * Created by fuzhipeng on 16/8/23.
 * 仅仅借用了ObjectAnimator source
 */
class ObjectAnimatorProxy : Cloneable {
    companion object {

        fun ofFloat(propertyName: String, vararg values: Float): ObjectAnimatorProxy {
            return ObjectAnimatorProxy(ObjectAnimator.ofFloat(null, propertyName, *values))
        }

        fun ofFloat(target: Any, propertyName: String, vararg values: Float): ObjectAnimatorProxy {
            return ObjectAnimatorProxy(ObjectAnimator.ofFloat(target, propertyName, *values))
        }


        fun ofInt(propertyName: String, vararg values: Int): ObjectAnimatorProxy {
            return ObjectAnimatorProxy(ObjectAnimator.ofInt(null, propertyName, *values))
        }

        fun ofInt(target: Any, propertyName: String, vararg values: Int): ObjectAnimatorProxy {
            return ObjectAnimatorProxy(ObjectAnimator.ofInt(target, propertyName, *values))
        }

        fun ofObject(propertyName: String, evaluator: TypeEvaluator<*>, vararg values: Any): ObjectAnimatorProxy {
            val nothing = Any()
            val source = ObjectAnimator.ofObject(nothing, propertyName, evaluator, *values).apply {
                target = null
            }
            return ObjectAnimatorProxy(source)
        }

        fun ofObject(target: Any, propertyName: String, evaluator: TypeEvaluator<*>, vararg values: Any): ObjectAnimatorProxy {
            return ObjectAnimatorProxy(ObjectAnimator.ofObject(target, propertyName, evaluator, *values))
        }
    }

    private lateinit var source: ObjectAnimator

    constructor() {
        source = ObjectAnimator()
    }

    private constructor(source: ObjectAnimator) {
        this.source = source
    }

    val values: Array<PropertyValuesHolder>
        get() = source.values


    val duration: Long
        get() = source.duration

    val currentPlayTime: Long
        get() = source.currentPlayTime

    val startDelay: Long
        get() = source.startDelay

    val animatedValue: Any
        get() = source.animatedValue

    val repeatCount: Int
        get() = source.repeatCount

    val repeatMode: Int
        get() = source.repeatMode

    val interpolator: TimeInterpolator
        get() = source.interpolator

    val isRunning: Boolean
        get() = source.isRunning

    val isStarted: Boolean
        get() = source.isStarted

    val animatedFraction: Float
        get() = source.animatedFraction

    val listeners: ArrayList<Animator.AnimatorListener>
        get() = source.listeners

    fun setCurrentPlayTime(playTime: Long): ObjectAnimatorProxy {
        source.currentPlayTime = playTime
        return this
    }

    fun getAnimatedValue(propertyName: String): Any {
        return source.getAnimatedValue(propertyName)
    }

    fun cancel(): ObjectAnimatorProxy {
        source.cancel()
        return this
    }

    fun end(): ObjectAnimatorProxy {
        source.end()
        return this
    }

    override fun toString(): String {
        return source.toString()
    }

    fun addListener(listener: Animator.AnimatorListener): ObjectAnimatorProxy {
        source.addListener(listener)
        return this
    }

    fun removeListener(listener: Animator.AnimatorListener): ObjectAnimatorProxy {
        source.removeListener(listener)
        return this
    }

    fun removeAllListeners(): ObjectAnimatorProxy {
        source.removeAllListeners()
        return this
    }

    // ---------------------ValueAnimatorProxy-----------------------------
    fun setIntValues(vararg values: Int): ObjectAnimatorProxy {
        source.setIntValues(*values)
        return this
    }


    fun setFloatValues(vararg values: Float): ObjectAnimatorProxy {
        source.setFloatValues(*values)
        return this
    }


    fun setValues(vararg values: PropertyValuesHolder): ObjectAnimatorProxy {
        source.setValues(*values)
        return this
    }


    fun setObjectValues(vararg values: Any): ObjectAnimatorProxy {
        source.setObjectValues(*values)
        return this
    }


    fun setDuration(duration: Long): ObjectAnimatorProxy {
        source.duration = duration
        return this
    }


    fun setStartDelay(startDelay: Long): ObjectAnimatorProxy {
        source.startDelay = startDelay
        return this
    }


    /**
     *
     * @param value   * @param value [ValueAnimator.INFINITE]
     * @return
     */
    fun setRepeatCount(value: Int): ObjectAnimatorProxy {
        source.repeatCount = value
        return this
    }


    fun setEvaluator(value: TypeEvaluator<*>): ObjectAnimatorProxy {
        source.setEvaluator(value)
        return this
    }


    fun removeAllUpdateListeners(): ObjectAnimatorProxy {
        source.removeAllUpdateListeners()
        return this
    }


    fun removeUpdateListener(listener: ValueAnimator.AnimatorUpdateListener): ObjectAnimatorProxy {
        source.removeUpdateListener(listener)
        return this
    }


    fun setInterpolator(value: Interpolator): ObjectAnimatorProxy {
        source.interpolator = value
        return this
    }


    //会调用start并 反转动画 但是start调用的却不是翻转;
    fun reverse(): ObjectAnimatorProxy {
        source.reverse()
        return this
    }

    /**
     * @param value [ValueAnimator.REVERSE][ValueAnimator.RESTART]
     * @return
     */
    fun setRepeatMode(value: Int): ObjectAnimatorProxy {
        source.repeatMode = value
        return this
    }


    fun addUpdateListener(listener: ValueAnimator.AnimatorUpdateListener): ObjectAnimatorProxy {
        source.addUpdateListener(listener)
        return this
    }


    fun setTarget(target: Any?): ObjectAnimatorProxy {
        source.target = target
        return this
    }


    fun setupStartValues(): ObjectAnimatorProxy {
        source.setupStartValues()
        return this
    }


    fun setupEndValues(): ObjectAnimatorProxy {
        source.setupEndValues()
        return this
    }

    fun start(): ObjectAnimatorProxy {
        source.start()
        return this
    }

    // ---------------------ObjectAnimator-----------------------------

    fun setProperty(property: Property<*, *>): ObjectAnimatorProxy {
        source.setProperty(property)
        return this
    }

    fun setPropertyName(propertyName: String): ObjectAnimatorProxy {
        source.propertyName = propertyName
        return this
    }

    fun source(): ObjectAnimator {
        return source
    }

    public override fun clone(): ObjectAnimatorProxy {
        return ObjectAnimatorProxy(this.source.clone())
    }


}
