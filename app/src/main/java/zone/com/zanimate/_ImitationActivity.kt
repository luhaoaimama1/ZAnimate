package zone.com.zanimate

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

import com.zone.lib.base.activity.BaseFragmentActivity
import com.zone.lib.base.activity.kinds.SwipeBackKind
import com.zone.lib.utils.view.ViewUtils

import butterknife.BindView
import butterknife.ButterKnife
import zone.com.zanimate.value.ValueAnimatorProxy
import zone.com.zanimate.view.Roll3DImageView_
import zone.com.zanimate.view.Roll3DImageView_Lib

class _ImitationActivity : BaseFragmentActivity() {
    @BindView(R.id.atdv_seek_bar1)
    internal var atdvSeekBar1: SeekBar? = null
    @BindView(R.id.roll3DImageView_)
    internal var roll3DImageView: Roll3DImageView_? = null
    @BindView(R.id.roll3DImageView_Lib)
    internal var roll3DImageView_Lib: Roll3DImageView_Lib? = null
    private val valueAnimator: ValueAnimatorProxy? = null

    override fun updateKinds() {
        super.updateKinds()
        mKindControl.remove(SwipeBackKind::class.java)
    }

    //模仿的activity
    //1.Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
    override fun setContentView() {
        setContentView(R.layout.imitation)
        ButterKnife.bind(this)
        ViewUtils.recurrenceClipChildren(roll3DImageView!!, false)
        ViewUtils.recurrenceClipChildren(roll3DImageView_Lib!!, false)

    }

    override fun findIDs() {}

    override fun initData() {

    }

    override fun setListener() {

        //        valueAnimator = ValueAnimatorProxy.ofInt(0, 100)
        //                .setRepeatCount(ValueAnimator.INFINITE)
        //                .setDuration(3000)
        //                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        //                    @Override
        //                    public void onAnimationUpdate(ValueAnimator animation) {
        //                        int progress=(Integer) animation.getAnimatedValue();
        //                        roll3DImageView.setRotateDegree(progress);
        //                        roll3DImageView_Lib.setRotateDegree(progress);
        //                        atdvSeekBar1.setProgress(progress);
        //                    }
        //                }).start();
        atdvSeekBar1!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                roll3DImageView!!.setRotateDegree(progress.toFloat())
                roll3DImageView_Lib!!.setRotateDegree(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }

}
