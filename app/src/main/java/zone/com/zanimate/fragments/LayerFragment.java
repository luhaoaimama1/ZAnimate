package zone.com.zanimate.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;

import and.utils.activity_fragment_ui.ToastUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import zone.com.zanimate.R;
import zone.com.zanimate._3DActivity;
import zone.com.zanimate.entity.Property;
import zone.com.zanimate.value.ValueAnimatorProxy;

public class LayerFragment extends Fragment implements View.OnClickListener {

    public boolean isParent;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_x)
    TextView tvX;
    @Bind(R.id.tv_y)
    TextView tvY;
    @Bind(R.id.tv_z)
    TextView tvZ;
    @Bind(R.id.cb)
    CheckBox cb;
    @Bind(R.id.tv_xR)
    TextView tvXR;
    @Bind(R.id.tv_yR)
    TextView tvYR;
    @Bind(R.id.tv_zR)
    TextView tvZR;
    @Bind(R.id.et_value)
    EditText etValue;
    @Bind(R.id.tv_subtract)
    Button tvSubtract;
    @Bind(R.id.tv_add)
    Button tvAdd;
    @Bind(R.id.tv_reset)
    Button tvReset;
    @Bind(R.id.tv_resetAll)
    Button tv_resetAll;
    private Property property = new Property();
    private _3DActivity activity;
    TextView[] textViews = new TextView[6];
    TextView select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        ButterKnife.bind(this, view);
        activity = (_3DActivity) getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isParent) {
            tv_title.setText("layerParent");
            etValue.setText("60");
        } else
            tv_title.setText("layer");

        textViews[0] = tvX;
        textViews[1] = tvY;
        textViews[2] = tvZ;
        textViews[3] = tvXR;
        textViews[4] = tvYR;
        textViews[5] = tvZR;

        init();

        for (TextView textView : textViews) {
            textView.setOnClickListener(this);
        }
        tvSubtract.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvReset.setOnClickListener(this);
        tv_resetAll.setOnClickListener(this);


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    //todo iv的中心
                    property.setX(activity.iv.bt.getWidth() / 2);
                    property.setY(activity.iv.bt.getHeight() / 2);
                    textViews[0].setText("x:" + activity.iv.bt.getWidth() / 2);
                    textViews[1].setText("x:" + activity.iv.bt.getHeight() / 2);
                    refresh();
                } else {
                    property.setX(0);
                    property.setY(0);
                    textViews[0].setText("x:" + 0);
                    textViews[1].setText("x:" + 0);
                }
            }
        });

    }

    private void refresh() {
        if (isParent)
            activity.layerParent(property);
        else
            activity.layer(property);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {

        TextView temp = select;
        switch (v.getId()) {
            case R.id.tv_x:
                select = textViews[0];
                break;
            case R.id.tv_y:
                select = textViews[1];
                break;
            case R.id.tv_z:
                select = textViews[2];
                break;

            case R.id.tv_xR:
                select = textViews[3];
                break;
            case R.id.tv_yR:
                select = textViews[4];
                break;
            case R.id.tv_zR:
                select = textViews[5];
                break;

            case R.id.tv_subtract:
                updateValue(false);
                break;

            case R.id.tv_add:
                updateValue(true);
                break;

            case R.id.tv_resetAll:
                reset();
                break;
            case R.id.tv_reset:
                resetSelect();
                break;
        }
        if (temp != select)
            uiUpdate();
    }

    private void resetSelect() {
        int index = -1;
        for (int i = 0; i < textViews.length; i++) {
            if (textViews[i] == select)
                index = i;
        }
        switch (index) {
            case 0:
                property.setX(0);
                select.setText("x:" + 0);
                break;
            case 1:
                property.setY(0);
                select.setText("y:" + 0);
                break;
            case 2:
                property.setZ(0);
                select.setText("z:" + 0);
                break;
            case 3:
                property.setxR(0);
                select.setText("xR:" + 0);
                break;
            case 4:
                property.setyR(0);
                select.setText("yR:" + 0);
                break;
            case 5:
                property.setzR(0);
                select.setText("zR:" + 0);
                break;
        }
        refresh();
    }

    int duration = 800;

    public void updateValue(boolean isAdd) {
        int number;
        try {
            number = Integer.parseInt(String.valueOf(etValue.getText()));

        } catch (NumberFormatException e) {
            ToastUtils.showLong(getActivity(), "editText必须是数字!");
            return;
        }
        if (number == 0)
            return;
        int index = -1;
        for (int i = 0; i < textViews.length; i++) {
            if (textViews[i] == select)
                index = i;
        }

        switch (index) {
            case 0:
                ValueAnimatorProxy.ofInt(property.getX(),
                        property.getX() + (isAdd ? number : -1 * number)).setDuration(duration).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        property.setX(value);
                        select.setText("x:" + value);
                        refresh();
                    }
                }).start();
                break;
            case 1:
                ValueAnimatorProxy.ofInt((int) property.getY(),
                        property.getY() + (isAdd ? number : -1 * number)).setDuration(duration).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        property.setY(value);
                        select.setText("y:" + value);
                        refresh();
                    }
                }).start();

                break;
            case 2:
                ValueAnimatorProxy.ofInt((int) property.getZ(),
                        property.getZ() + (isAdd ? number : -1 * number)).setDuration(duration).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        property.setZ(value);
                        select.setText("z:" + value);
                        refresh();
                    }
                }).start();
                break;
            case 3:
                ValueAnimatorProxy.ofInt((int) property.getxR(),
                        property.getxR() + (isAdd ? number : -1 * number)).setDuration(duration).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        property.setxR(value);
                        select.setText("xR:" + value);
                        refresh();
                    }
                }).start();
                break;
            case 4:
                ValueAnimatorProxy.ofInt((int) property.getyR(),
                        property.getyR() + (isAdd ? number : -1 * number)).setDuration(duration).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        property.setyR(value);
                        select.setText("yR:" + value);
                        refresh();
                    }
                }).start();
                break;
            case 5:
                ValueAnimatorProxy.ofInt((int) property.getzR(),
                        property.getzR() + (isAdd ? number : -1 * number)).setDuration(duration).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        property.setzR(value);
                        select.setText("zR:" + value);
                        refresh();
                    }
                }).start();
                break;
        }

    }

    private void init() {
        select = textViews[0];
        uiUpdate();
        textViews[0].setText("x:0");
        textViews[1].setText("y:0");
        textViews[2].setText("z:0");
        textViews[3].setText("xR:0");
        textViews[4].setText("yR:0");
        textViews[5].setText("zR:0");
    }

    private void reset() {
        select = textViews[0];
        property = new Property();
        cb.setChecked(false);
        refresh();
        uiUpdate();
        textViews[0].setText("x:0");
        textViews[1].setText("y:0");
        textViews[2].setText("z:0");
        textViews[3].setText("xR:0");
        textViews[4].setText("yR:0");
        textViews[5].setText("zR:0");
    }

    private void uiUpdate() {
        for (TextView textView : textViews) {
            if (textView == select)
                textView.setBackgroundColor(Color.BLUE);
            else
                textView.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}