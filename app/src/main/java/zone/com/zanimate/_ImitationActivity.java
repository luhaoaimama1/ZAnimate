package zone.com.zanimate;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;

import and.base.activity.BaseFragmentActivity;
import and.base.activity.kinds.SwipeBackKind;
import and.utils.view.ViewUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import zone.com.zanimate.value.ValueAnimatorProxy;
import zone.com.zanimate.view.Roll3DImageView_;
import zone.com.zanimate.view.Roll3DImageView_Lib;

public class _ImitationActivity extends BaseFragmentActivity {
    @Bind(R.id.atdv_seek_bar1)
    SeekBar atdvSeekBar1;
    @Bind(R.id.roll3DImageView_)
    Roll3DImageView_ roll3DImageView;
    @Bind(R.id.roll3DImageView_Lib)
    Roll3DImageView_Lib roll3DImageView_Lib;
    private ValueAnimatorProxy valueAnimator;

    @Override
    public void updateKinds() {
        super.updateKinds();
        mKindControl.remove(SwipeBackKind.class);
    }

    //模仿的activity
    //1.Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
    @Override
    public void setContentView() {
        setContentView(R.layout.imitation);
        ButterKnife.bind(this);
        ViewUtils.recurrenceClipChildren(roll3DImageView, false);
        ViewUtils.recurrenceClipChildren(roll3DImageView_Lib, false);

    }

    @Override
    public void findIDs() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

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
        atdvSeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                roll3DImageView.setRotateDegree(progress);
                roll3DImageView_Lib.setRotateDegree(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
