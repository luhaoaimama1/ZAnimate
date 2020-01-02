package zone.com.zanimate

import android.widget.SeekBar

import com.zone.lib.base.activity.BaseFragmentActivity
import com.zone.lib.base.activity.kinds.SwipeBackKind
import com.zone.lib.utils.view.ViewUtils

import butterknife.ButterKnife
import kotlinx.android.synthetic.main.imitation.*
import zone.com.zanimate.value.ValueAnimatorProxy

class _ImitationActivity : BaseFragmentActivity() {

    override fun updateKinds() {
        super.updateKinds()
        mKindControl.remove(SwipeBackKind::class.java)
    }

    //模仿的activity
    //1.Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
    override fun setContentView() {
        setContentView(R.layout.imitation)
        ViewUtils.recurrenceClipChildren(roll3DImageView_, false)
        ViewUtils.recurrenceClipChildren(roll3DImageView_Lib, false)

    }

    override fun findIDs() {}

    override fun initData() {

    }

    override fun setListener() {
        atdv_seek_bar1!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                roll3DImageView_.setRotateDegree(progress.toFloat())
                roll3DImageView_Lib.setRotateDegree(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }

}
