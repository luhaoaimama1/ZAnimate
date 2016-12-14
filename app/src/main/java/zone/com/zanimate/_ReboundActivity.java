package zone.com.zanimate;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.view.ViewHelper;
import com.zone.view.SquareImageView2;
import java.util.List;
import and.utils.view.ViewUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class _ReboundActivity extends AppCompatActivity {

    @Bind(R.id.siv)
    SquareImageView2 siv;
    @Bind(R.id.ll_main)
    LinearLayout ll_main;
    @Bind(R.id.seekBarFriction)
    SeekBar seekBarFriction;
    @Bind(R.id.seekTension)
    SeekBar seekTension;
    @Bind(R.id.tv_Friction)
    TextView tv_Friction;
    @Bind(R.id.tv_Tension)
    TextView tv_Tension;
    private Spring spring;
    private int tensionValue = (int) SpringConfig.defaultConfig.tension;
    private int frictionValue = (int) SpringConfig.defaultConfig.friction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_rebound);
        ButterKnife.bind(this);
        spring =  SpringSystem.create().createSpring();

        addReboundListener();
        updateConfig();
        updateSeekbar();

    }

    private void addReboundListener() {
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                double currentValue = spring.getCurrentValue();
                double valueMap = SpringUtil.mapValueFromRangeToRange(currentValue, 0, 1, 1, 0.5);
                ViewHelper.setScaleX(siv, (float) valueMap);
                ViewHelper.setScaleY(siv, (float) valueMap);
            }
        });

        siv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        spring.setEndValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        spring.setEndValue(0);
                        break;
                }
                return true;
            }
        });
    }


    int startColor = Color.argb(255, 255, 64, 230);
    int endColor = Color.argb(255, 0, 174, 255);

    @OnClick({R.id.playTogether, R.id.play})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                siv.bringToFront();
                spring.setEndValue(spring.getEndValue() == 0 ? 1 : 0);
                break;
            case R.id.playTogether:
                playTogether();
                break;

        }
    }

    private void playTogether() {
        ll_main.bringToFront();
        ll_main.removeAllViews();
        final SpringChain mSpringChain = SpringChain.create();
        final int offset = (ll_main.getRootView().getWidth() + ll_main.getWidth()) / 2;
        for (int i = 0; i < 7; i++) {
            final View v = new View(this);
            ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            v.setBackgroundColor((Integer) argbEvaluator.evaluate((float) i / (float) 7, startColor, endColor));
            ll_main.addView(v, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
            v.setTranslationX(-offset);
            mSpringChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    float value = (float) spring.getCurrentValue();
                    v.setTranslationX(value);
                }
                @Override
                public void onSpringAtRest(Spring spring) {
                    super.onSpringAtRest(spring);
                    v.setVisibility(View.INVISIBLE);
                }
            });
            if (i == 6)
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<Spring> springList = mSpringChain.getAllSprings();
                        for (Spring spring1 : springList)
                            spring1.setCurrentValue(-offset);
                        for (int i1 = 0; i1 < ll_main.getChildCount(); i1++)
                            ViewUtils.recurrenceClipChildren(ll_main.getChildAt(i1), false);
                        mSpringChain.setControlSpringIndex(2).getControlSpring().setEndValue(0);
                    }
                }, 500);
        }
    }


    private void updateSeekbar() {
        seekTension.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                tensionValue = progress;
                updateConfig();
            }
        });
        seekBarFriction.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                frictionValue = progress;
                updateConfig();
            }
        });
    }

    private void updateConfig() {
        tv_Tension.setText(" 拉力(tension):" + tensionValue);
        tv_Friction.setText(" 摩擦力(friction):" + frictionValue);
        seekTension.setProgress(tensionValue);
        seekBarFriction.setProgress(frictionValue);
        spring.setSpringConfig(new SpringConfig(tensionValue, frictionValue));
    }


    public static class SimpleOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}