package zone.com.zanimate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zone.com.zanimate.object.ObjectAnimatorHelper;
import zone.com.zanimate.object.plugins.ExampleAnimator;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tv2)
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new ViewWrap().start();
    }

    @OnClick({R.id.playTogether, R.id.playSort,R.id.playPreset})
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
        }
    }

    private void playPreset() {
        ObjectAnimatorHelper.playPreset(ExampleAnimator.class).setTarget(tv).start();
    }

    private void playSort() {
        // 特定顺序的动画
        ObjectAnimatorHelper
                .play(ObjectAnimatorHelper.ofFloatProxy( "translationX", 0F, 300F).setStartDelay(1000))
                .with(ObjectAnimatorHelper.ofFloatProxy("alpha", 1F, 0.5F, 1F).setStartDelay(1000))//这个应该第一个after播放
                .after(ObjectAnimatorHelper.ofFloat( "translationY", 0F, 300F))
                .after(ObjectAnimatorHelper.ofFloat("translationX", 300F, 0F))
                .after(ObjectAnimatorHelper.ofFloat( "translationY", 300F, 0F),1000)
                .with(ObjectAnimatorHelper.ofFloat("alpha", 1F, 0.5F, 1F))
                .setTarget(tv)
                .setDuration(1000)
                .start();
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
