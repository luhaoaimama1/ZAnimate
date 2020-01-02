package zone.com.zanimate;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.animation.ValueAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zone.com.zanimate.object.ObjectAnimatorHelper;
import zone.com.zanimate.object.SortAnimator;
import zone.com.zanimate.object.plugins.ExampleAnimator;
import zone.com.zanimate.value.ValueAnimatorProxy;

public class _2DActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_2d);
        ButterKnife.bind(this);
        new ViewWrap().start();
    }

    @OnClick({R.id.playTogether, R.id.playSort, R.id.playPreset, R.id.valueAnimator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playTogether:
                playTogether();
                break;
            case R.id.playSort:
                playSort();
                break;
            case R.id.playPreset:
                playPreset();
                break;
            case R.id.valueAnimator:
                valueAnimator();
                break;
        }
        if (view.getId() != R.id.valueAnimator && valueAnimator != null)
            valueAnimator.cancel();
        if (view.getId() != R.id.playSort && sortAnimator != null)
            sortAnimator.cancel();
    }

    private ValueAnimator valueAnimator;

    private void valueAnimator() {
        mProgressBar.setMax(100);
        if (valueAnimator == null)
            valueAnimator = ValueAnimatorProxy.ofInt(0, 100)
                    .setRepeatCount(ValueAnimator.INFINITE)
                    .setDuration(1500)
                    .setRepeatMode(ValueAnimator.REVERSE)
                    .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mProgressBar.setProgress((Integer) animation.getAnimatedValue());
                        }
                    }).source();//可以直接start
        if (!valueAnimator.isRunning())
            valueAnimator.start();
    }

    private void playPreset() {
        ObjectAnimatorHelper.playPreset(ExampleAnimator.class).setTarget(tv).start();
    }

    private SortAnimator sortAnimator;

    private void playSort() {
        if (sortAnimator == null)
            // 特定顺序的动画
            sortAnimator = ObjectAnimatorHelper
                    .play(ObjectAnimatorHelper.ofFloatProxy("translationX", 0F, 300F).setStartDelay(1000))
                    .with(ObjectAnimatorHelper.ofFloatProxy("alpha", 1F, 0.5F, 1F).setStartDelay(1000))//这个应该第一个after播放
                    .after(ObjectAnimatorHelper.ofFloat("translationY", 0F, 300F))
                    .after(ObjectAnimatorHelper.ofFloat("translationX", 300F, 0F))
                    .after(ObjectAnimatorHelper.ofFloat("translationY", 300F, 0F), 1000)
                    .with(ObjectAnimatorHelper.ofFloat("alpha", 1F, 0.5F, 1F))
                    .setTarget(tv)
                    .setDuration(1000);
        if (!sortAnimator.isRunning())
            sortAnimator.start();
    }

    private void playTogether() {
        //封装动画
        ObjectAnimatorHelper.playTogether(
                ObjectAnimatorHelper.ofFloat("alpha", 1F, 0.5F, 1F),
                ObjectAnimatorHelper.ofFloat("scaleX", 1F, 3F, 1F),
                ObjectAnimatorHelper.ofFloat("scaleY", 1F, 3F, 1F)
        ).setTarget(tv, tv2).setDuration(600).start();
    }
}
