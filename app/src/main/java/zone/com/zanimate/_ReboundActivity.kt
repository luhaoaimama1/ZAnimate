package zone.com.zanimate

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringChain
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem
import com.facebook.rebound.SpringUtil
import com.zone.lib.utils.view.ViewUtils
import com.zone.view.SquareImageView2
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.a_rebound.*

class _ReboundActivity : AppCompatActivity() {

    lateinit var spring: Spring
    private var tensionValue = SpringConfig.defaultConfig.tension.toInt()
    private var frictionValue = SpringConfig.defaultConfig.friction.toInt()


    internal var startColor = Color.argb(255, 255, 64, 230)
    internal var endColor = Color.argb(255, 0, 174, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_rebound)
        ButterKnife.bind(this)
        spring = SpringSystem.create().createSpring()

        addReboundListener()
        updateConfig()
        updateSeekbar()

    }

    private fun addReboundListener() {
        spring.addListener(object : SimpleSpringListener() {
            override fun onSpringUpdate(spring: Spring?) {
                super.onSpringUpdate(spring)
                val currentValue = spring!!.currentValue
                val valueMap = SpringUtil.mapValueFromRangeToRange(currentValue, 0.0, 1.0, 1.0, 0.5)
                siv.scaleX = valueMap.toFloat()
                siv.scaleY = valueMap.toFloat()
            }
        })

        siv.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> spring.endValue = 1.0
                MotionEvent.ACTION_UP -> spring.endValue = 0.0
            }
            true
        }
    }

    @OnClick(R.id.playTogether, R.id.play)
    fun onClick(view: View) {
        when (view.id) {
            R.id.play -> {
                siv!!.bringToFront()
                spring.endValue = (if (spring.endValue == 0.0) 1 else 0).toDouble()
            }
            R.id.playTogether -> playTogether()
        }
    }

    private fun playTogether() {
        ll_main!!.bringToFront()
        ll_main!!.removeAllViews()
        val mSpringChain = SpringChain.create()
        val offset = (ll_main!!.rootView.width + ll_main!!.width) / 2
        for (i in 0..6) {
            val v = View(this)
            val argbEvaluator = ArgbEvaluator()
            v.setBackgroundColor(argbEvaluator.evaluate(i.toFloat() / 7.toFloat(), startColor, endColor) as Int)
            ll_main!!.addView(v, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f))
            v.translationX = (-offset).toFloat()
            mSpringChain.addSpring(object : SimpleSpringListener() {
                override fun onSpringUpdate(spring: Spring?) {
                    val value = spring!!.currentValue.toFloat()
                    v.translationX = value
                }

                override fun onSpringAtRest(spring: Spring?) {
                    super.onSpringAtRest(spring)
                    v.visibility = View.INVISIBLE
                }
            })
            if (i == 6)
                v.postDelayed({
                    val springList = mSpringChain.allSprings
                    for (spring1 in springList)
                        spring1.currentValue = (-offset).toDouble()
                    for (i1 in 0 until ll_main!!.childCount)
                        ViewUtils.recurrenceClipChildren(ll_main!!.getChildAt(i1), false)
                    mSpringChain.setControlSpringIndex(2).controlSpring.endValue = 0.0
                }, 500)
        }
    }


    private fun updateSeekbar() {
        seekTension.setOnSeekBarChangeListener(object : SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                tensionValue = progress
                updateConfig()
            }
        })
        seekBarFriction.setOnSeekBarChangeListener(object : SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                frictionValue = progress
                updateConfig()
            }
        })
    }

    private fun updateConfig() {
        tv_Tension.text = " 拉力(tension):$tensionValue"
        tv_Friction.text = " 摩擦力(friction):$frictionValue"
        seekTension.progress = tensionValue
        seekBarFriction!!.progress = frictionValue
        spring.springConfig = SpringConfig(tensionValue.toDouble(), frictionValue.toDouble())
    }


    open class SimpleOnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {

        }
    }
}
