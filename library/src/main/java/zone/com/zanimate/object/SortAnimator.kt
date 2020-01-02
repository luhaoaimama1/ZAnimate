package zone.com.zanimate.`object`

import android.animation.Animator
import android.animation.AnimatorListenerAdapter

import java.util.ArrayList

/**
 * Created by fuzhipeng on 16/8/23.
 */
class SortAnimator {
    private val sortList: MutableList<AnimatorWithIndex> by lazy {
        ArrayList<AnimatorWithIndex>()
    }
    internal var cancel = false

    //play after 添加坚挺  with不需要
    private val listener = object : AnimatorListenerAdapter() {

        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
            //with
            var obj: AnimatorWithIndex? = null
            for (i in sortList.indices) {
                val item = sortList[i]
                if (obj != null && item.index == obj.index) {
                    item.animator.start()
                }
                //找到当前的位置;
                if (animation === item.animator) {
                    obj = item
                }
            }

        }

        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            runAfter(animation)

        }

        //after
        private fun runAfter(animation: Animator) {
            animation.removeListener(this)//保证  每个animation runAfter仅仅执行一次;
            var index = -1
            for (i in sortList.indices) {
                if (animation === sortList[i].animator) {
                    if (!sortList[i].isWith)
                    //找到当前结束并且不是  with类型的 的animation的index;
                        index = sortList[i].index
                }
                if (!cancel) {//没有主动退出的时候 找下一个动画

                    //如果play结束找到其位置后，那么找到after动画 并执行
                    val item = sortList[i]
                    if (index != -1 && item.index == index + 1) {
                        item.animator.addListener(this)
                        item.animator.start()
                        break
                    }
                }
            }
        }

        override fun onAnimationRepeat(animation: Animator) {
            super.onAnimationRepeat(animation)
            runAfter(animation)
        }

    }

    val isRunning: Boolean
        get() {
            var isRunning = false
            for (animatorWithIndex in sortList)
                if (animatorWithIndex.animator.isRunning)
                    isRunning = true
            return isRunning
        }

    private val lastAnimatorWithIndex: AnimatorWithIndex
        get() {
            checkSortBuilder()
            return sortList[sortList.size - 1]
        }


    fun play(anim: Animator): SortAnimator {
        sortList.add(AnimatorWithIndex(anim, 0))
        return this
    }


    fun play(anim: ObjectAnimatorProxy): SortAnimator {
        return play(anim.source())
    }

    fun with(anim: Animator): SortAnimator {
        val lastIndex = lastAnimatorWithIndex.index
        val animatorWithIndex = AnimatorWithIndex(anim, lastIndex)
        animatorWithIndex.isWith = true
        sortList.add(animatorWithIndex)
        return this
    }

    fun with(anim: ObjectAnimatorProxy): SortAnimator {
        return with(anim.source())
    }

    fun after(anim: ObjectAnimatorProxy): SortAnimator {
        return after(anim.source(), 0)
    }

    fun start(): SortAnimator {
        checkSortBuilder()
        cancel = false
        val animatorWithIndex = sortList[0]
        animatorWithIndex.animator.addListener(listener)
        animatorWithIndex.animator.start()

        return this
    }

    @JvmOverloads
    fun after(anim: Animator, delay: Long = 0): SortAnimator {
        val lastIndex = lastAnimatorWithIndex.index
        sortList.add(AnimatorWithIndex(anim, lastIndex + 1, delay))
        return this
    }

    //如果这个设置了 那么所有的 动画全被被迭代设置; 可以进去看看
    fun setDuration(duration: Long): SortAnimator {
        checkSortBuilder()
        for (animatorWithIndex in sortList)
            animatorWithIndex.animator.duration = duration
        return this
    }

    fun setTarget(obj: Any): SortAnimator {
        checkSortBuilder()
        for (animatorWithIndex in sortList)
            animatorWithIndex.animator.setTarget(obj)
        return this
    }

    fun cancel(): SortAnimator {
        cancel = true
        for (animatorWithIndex in sortList) {
            if (animatorWithIndex.animator.isRunning)
                animatorWithIndex.animator.cancel()
        }
        return this
    }

    private fun checkSortBuilder() {
        if (sortList.size == 0)
            throw IllegalStateException("method:with,after. first must use method:play!")
    }

    internal inner class AnimatorWithIndex @JvmOverloads constructor(var animator: Animator, var index: Int, delay: Long = 0) {
        var delay: Long = 0
        var isWith: Boolean = false
        init {
            this.delay = animator.startDelay + delay
            animator.startDelay = this.delay
        }
    }
}
