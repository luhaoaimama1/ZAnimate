package zone.com.zanimate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zone.com.zanimate.object.ObjectAnimatorHelper;
import zone.com.zanimate.object.ObjectAnimatorProxy;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.test1)
    Button test1;
    @Bind(R.id.test2)
    Button test2;
    @Bind(R.id.test3)
    Button test3;
    @Bind(R.id.tv2)
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //animate动画
//        view.setTranslationX(Utils.getScreenHeight(context));
//        view.animate().translationX(0)
//                .setInterpolator(new DecelerateInterpolator(3.f))
//                .setDuration(1500)
//                .start();
    }

    @OnClick({R.id.test1, R.id.test2, R.id.test3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test1:
                test1();
                break;
            case R.id.test2:
                test2();
                break;
            case R.id.test3:
                test3();
                break;
        }
    }

    private void test2() {
//        ObjectAnimatorHelper.ofFloat(tv, "x", 0F, 300F).setDuration(1000).start();
//        ObjectAnimatorHelper.ofFloat(tv, "Y", 0F, 300F).setDuration(1000).start();
// 特定顺序的动画
        ObjectAnimator kb = ObjectAnimatorHelper.ofFloat(tv, "translationX", 0F, 300F);
        kb.setStartDelay(1000);
//        ObjectAnimator last = kb.clone();
//        last.reverse();
        ObjectAnimatorHelper
                .play(kb)
                .with(ObjectAnimatorHelper.ofFloat(tv,"alpha", 1F, 0.5F, 1F))
                .after(ObjectAnimatorHelper.ofFloat(tv, "translationY", 0F, 300F))
                .after(ObjectAnimatorHelper.ofFloat(tv, "translationX", 300F, 0F))
                .after(ObjectAnimatorHelper.ofFloat(tv, "translationY", 300F, 0F),1000)
                .with(ObjectAnimatorHelper.ofFloat(tv,"alpha", 1F, 0.5F, 1F))
                .setDuration(1000)
                .start();
    }

    //todo 需要做个 特定顺序的
    private void test3() {
        //发现 after在前边,play在after 300以后播放,with和play一起播放, before最后播放;  真JB蛋疼;
//// 特定顺序的动画
//        ObjectAnimatorHelper.play(ObjectAnimatorHelper.ofFloat(tv,"translationX", 1F,100F, 1F))
//                .with(ObjectAnimatorHelper.ofFloat(tv,"rotationY", 0, 180, 0))
//                .before(ObjectAnimatorHelper.ofFloat(tv,"scaleY", 1F, 3F, 1F))
//                .after(300)
//                .after(ObjectAnimatorHelper.ofFloat(tv2,"translationY", 1F, 100F, 1F))
//                .with(ObjectAnimatorHelper.ofFloat(tv2,"rotationY", 0, 180, 0)).start();

//        ObjectAnimatorHelper
//                .play(ObjectAnimatorHelper.ofFloatProxy(tv, "translationX", 0, 300).setStartDelay(1000).reverse())
//                .with(ObjectAnimatorHelper.ofFloatProxy(tv,"alpha", 1F, 0.5F, 1F))
//                .after(ObjectAnimatorHelper.ofFloat(tv, "translationY", 0F, 300F))
//                .after(ObjectAnimatorHelper.ofFloat(tv, "translationX", 300F, 0F))
//                .after(ObjectAnimatorHelper.ofFloat(tv, "translationY", 300F, 0F),1000)
//                .with(ObjectAnimatorHelper.ofFloat(tv,"alpha", 1F, 0.5F, 1F))
//                .setDuration(1000)
//                .start();
//        ObjectAnimatorHelper.play()
//                .setStartDelay().setStartDelay(1000).setDuration(1000).start();
    }

    private void test1() {
        //封装动画
        ObjectAnimatorHelper.playTogether(
                ObjectAnimatorHelper.ofFloat("alpha", 1F, 0.5F, 1F),
                ObjectAnimatorHelper.ofFloat("scaleX", 1F, 0.8F, 1F),
                ObjectAnimatorHelper.ofFloat("scaleY", 1F, 0.8F, 1F)
        ).setTarget(tv, tv2).setDuration(600).start();
    }
}
