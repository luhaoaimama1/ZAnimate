package zone.com.zanimate.`object`

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.view.View

/**
 * Created by zone on 2016/6/27.
 * 特定顺序：ObjectAnimatorHelper.newInstance().play(bounceAnim).before(squashAnim1).with(stretchAnim2).after(stretchAnim2);
 */
object ObjectAnimatorHelper {
    var DURATION: Long = 800
    val any = Any()

    private fun newInstance(): AnimatorSet {
        val mAnimatorSet = AnimatorSet()
        mAnimatorSet.duration = DURATION
        return mAnimatorSet
    }

    fun playSequentially(vararg items: Animator): AnimatorSetProxy {
        val mAnimatorSet = newInstance()
        mAnimatorSet.playSequentially(*items)
        return AnimatorSetProxy(mAnimatorSet)
    }

    fun playSequentiallyProxy(vararg items: ObjectAnimatorProxy): AnimatorSetProxy {
        val aniArr = getAniArr(items.toList())
        return playSequentially(*aniArr)
    }

    private fun getAniArr(items: List<ObjectAnimatorProxy>): Array<Animator> {
        val aniArr = arrayOfNulls<Animator>(items.size)
        for (i in items.indices)
            aniArr[i] = items[i].source()
        return aniArr as Array<Animator>
    }

    fun playSequentially(items: List<Animator>): AnimatorSetProxy {
        val mAnimatorSet = newInstance()
        mAnimatorSet.playSequentially(items)
        return AnimatorSetProxy(mAnimatorSet)
    }

    fun playSequentiallyProxy(items: List<ObjectAnimatorProxy>): AnimatorSetProxy {
        val aniArr = getAniArr(items)
        return playSequentially(*aniArr)
    }

    fun playTogether(vararg items: Animator): AnimatorSetProxy {
        val mAnimatorSet = newInstance()
        mAnimatorSet.playTogether(*items)
        return AnimatorSetProxy(mAnimatorSet)
    }

    fun playTogetherProxy(vararg items: ObjectAnimatorProxy): AnimatorSetProxy {
        val aniArr = getAniArr(items.toList())
        return playTogether(*aniArr)
    }


    fun playTogether(items: List<Animator>): AnimatorSetProxy {
        val mAnimatorSet = newInstance()
        mAnimatorSet.playTogether(items)
        return AnimatorSetProxy(mAnimatorSet)
    }

    fun playTogetherProxy(items: List<ObjectAnimatorProxy>): AnimatorSetProxy {
        val aniArr = getAniArr(items)
        return playTogether(*aniArr)
    }


    fun playPreset(mBaseViewAnimator: BaseViewAnimator): AnimatorSetProxy {
        val mAnimatorSet = newInstance()
        mBaseViewAnimator.prepare(mAnimatorSet)
        return AnimatorSetProxy(mAnimatorSet)
    }


    fun playPreset(mBaseViewAnimator: Class<out BaseViewAnimator>): AnimatorSetProxy? {
        try {
            return playPreset(mBaseViewAnimator.newInstance())
        } catch (e: InstantiationException) {
            e.printStackTrace()
            return null
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return null
        }

    }

    fun ofFloat(propertyName: String, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(null, propertyName, *values)
    }

    fun ofFloat(target: Any, propertyName: String, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(target, propertyName, *values)
    }

    fun ofFloatProxy(propertyName: String, vararg values: Float): ObjectAnimatorProxy {
        return ObjectAnimatorProxy.ofFloat(any, propertyName, *values).apply {
            setTarget(null)
        }
    }

    fun ofFloatProxy(target: Any, propertyName: String, vararg values: Float): ObjectAnimatorProxy {
        return ObjectAnimatorProxy.ofFloat(target, propertyName, *values)
    }


    fun ofInt(propertyName: String, vararg values: Int): ObjectAnimator {
        return ObjectAnimator.ofInt(null, propertyName, *values)
    }

    fun ofInt(target: Any, propertyName: String, vararg values: Int): ObjectAnimator {
        return ObjectAnimator.ofInt(target, propertyName, *values)
    }

    fun ofIntProxy(propertyName: String, vararg values: Int): ObjectAnimatorProxy {
        return ObjectAnimatorProxy.ofInt(any, propertyName, *values).apply {
            setTarget(null)
        }
    }

    fun ofIntProxy(target: Any, propertyName: String, vararg values: Int): ObjectAnimatorProxy {
        return ObjectAnimatorProxy.ofInt(target, propertyName, *values)
    }


    fun ofObject(propertyName: String, evaluator: TypeEvaluator<*>, vararg values: Any): ObjectAnimator {
        return ObjectAnimator.ofObject(any, propertyName, evaluator, *values).apply {
            target = null
        }
    }
    fun ofObject(target: Any, propertyName: String, evaluator: TypeEvaluator<*>, vararg values: Any): ObjectAnimator {
        return ObjectAnimator.ofObject(target, propertyName, evaluator, *values)
    }

    fun ofObjectProxy(propertyName: String, evaluator: TypeEvaluator<*>, vararg values: Any): ObjectAnimatorProxy {
        return ObjectAnimatorProxy.ofObject(any, propertyName, evaluator, *values).apply {
            setTarget(null)
        }
    }

    fun ofObjectProxy(target: Any, propertyName: String, evaluator: TypeEvaluator<*>, vararg values: Any): ObjectAnimatorProxy {
        return ObjectAnimatorProxy.ofObject(target, propertyName, evaluator, *values)
    }

    // 特定顺序的动画   不需要设置时间
    // ObjectAnimatorHelper.play(bounceAnim).before(squashAnim1).with(stretchAnim2).after(stretchAnim2).start();
    fun play(mAnimator: Animator): SortAnimator {
        val result = SortAnimator()
        result.play(mAnimator)
        return result
    }

    // 特定顺序的动画   不需要设置时间
    // ObjectAnimatorHelper.play(bounceAnim).before(squashAnim1).with(stretchAnim2).after(stretchAnim2).start();
    fun play(mAnimator: ObjectAnimatorProxy): SortAnimator {
        return play(mAnimator.source())
    }

    //    PROXY_PROPERTIES.put("alpha", PreHoneycombCompat.ALPHA);
    //    PROXY_PROPERTIES.put("pivotX", PreHoneycombCompat.PIVOT_X);
    //    PROXY_PROPERTIES.put("pivotY", PreHoneycombCompat.PIVOT_Y);
    //    PROXY_PROPERTIES.put("translationX", PreHoneycombCompat.TRANSLATION_X);
    //    PROXY_PROPERTIES.put("translationY", PreHoneycombCompat.TRANSLATION_Y);
    //    PROXY_PROPERTIES.put("rotation", PreHoneycombCompat.ROTATION);
    //    PROXY_PROPERTIES.put("rotationX", PreHoneycombCompat.ROTATION_X);
    //    PROXY_PROPERTIES.put("rotationY", PreHoneycombCompat.ROTATION_Y);
    //    PROXY_PROPERTIES.put("scaleX", PreHoneycombCompat.SCALE_X);
    //    PROXY_PROPERTIES.put("scaleY", PreHoneycombCompat.SCALE_Y);
    //    PROXY_PROPERTIES.put("scrollX", PreHoneycombCompat.SCROLL_X);
    //    PROXY_PROPERTIES.put("scrollY", PreHoneycombCompat.SCROLL_Y);
    //    PROXY_PROPERTIES.put("x", PreHoneycombCompat.X);
    //    PROXY_PROPERTIES.put("y", PreHoneycombCompat.Y);
    fun reset(target: View) {
        target.alpha = 1f//字符串就是alpha set去掉第一个字母在变成小写;
        target.alpha = 1f
        target.scaleX = 1f
        target.scaleY = 1f
        target.translationX = 0f
        target.translationY = 0f
        target.rotation = 0f
        target.rotationY = 0f
        target.rotationX = 0f
        target.pivotX = target.measuredWidth / 2.0f
        target.pivotY = target.measuredHeight / 2.0f
    }

    //为了添加预设而用  //灵感来自 daimajia的AndroidViewAnimations
    interface BaseViewAnimator {
        fun prepare(mAnimatorSet: AnimatorSet)
    }
}
