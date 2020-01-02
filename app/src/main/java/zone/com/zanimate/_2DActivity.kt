package zone.com.zanimate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

import android.animation.ValueAnimator

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.a_2d.*
import zone.com.zanimate.`object`.ObjectAnimatorHelper
import zone.com.zanimate.`object`.SortAnimator
import zone.com.zanimate.`object`.plugins.ExampleAnimator
import zone.com.zanimate.value.ValueAnimatorProxy

class _2DActivity : AppCompatActivity() {


    private var valueAnimator: ValueAnimator? = null

    private var sortAnimator: SortAnimator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_2d)
        ButterKnife.bind(this)
        ViewWrap().start()
    }

    @OnClick(R.id.playTogether, R.id.playSort, R.id.playPreset, R.id.valueAnimator)
    fun onClick(view: View) {
        when (view.id) {
            R.id.playTogether -> playTogether()
            R.id.playSort -> playSort()
            R.id.playPreset -> playPreset()
            R.id.valueAnimator -> valueAnimator()
        }
        if (view.id != R.id.valueAnimator && valueAnimator != null)
            valueAnimator!!.cancel()
        if (view.id != R.id.playSort && sortAnimator != null)
            sortAnimator!!.cancel()
    }

    private fun valueAnimator() {
        progressBar.max = 100
        if (valueAnimator == null)
            valueAnimator = ValueAnimatorProxy.ofInt(0, 100)
                    .setRepeatCount(ValueAnimator.INFINITE)
                    .setDuration(1500)
                    .setRepeatMode(ValueAnimator.REVERSE)
                    .addUpdateListener { animation -> progressBar!!.progress = animation.animatedValue as Int }.source()//可以直接start
        if (!valueAnimator!!.isRunning)
            valueAnimator!!.start()
    }

    private fun playPreset() {
        ObjectAnimatorHelper.playPreset(ExampleAnimator::class.java)!!.setTarget(tv!!)!!.start()
    }

    private fun playSort() {
        if (sortAnimator == null)
        // 特定顺序的动画
            sortAnimator = ObjectAnimatorHelper
                    .play(ObjectAnimatorHelper.ofFloatProxy("translationX", 0f, 300f).setStartDelay(1000))
                    .with(ObjectAnimatorHelper.ofFloatProxy("alpha", 1f, 0.5f, 1f).setStartDelay(1000))//这个应该第一个after播放
                    .after(ObjectAnimatorHelper.ofFloat("translationY", 0f, 300f))
                    .after(ObjectAnimatorHelper.ofFloat("translationX", 300f, 0f))
                    .after(ObjectAnimatorHelper.ofFloat("translationY", 300f, 0f), 1000)
                    .with(ObjectAnimatorHelper.ofFloat("alpha", 1f, 0.5f, 1f))
                    .setTarget(tv)
                    .setDuration(1000)
        if (!sortAnimator!!.isRunning)
            sortAnimator!!.start()
    }

    private fun playTogether() {
        //封装动画
        ObjectAnimatorHelper.playTogether(
                ObjectAnimatorHelper.ofFloat("alpha", 1f, 0.5f, 1f),
                ObjectAnimatorHelper.ofFloat("scaleX", 1f, 3f, 1f),
                ObjectAnimatorHelper.ofFloat("scaleY", 1f, 3f, 1f)
        ).setTarget(tv, tv2)!!.setDuration(600).start()
    }
}
