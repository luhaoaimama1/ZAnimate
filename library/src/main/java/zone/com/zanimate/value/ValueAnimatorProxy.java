package zone.com.zanimate.value;

import android.view.animation.Interpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;

/**
 * Created by fuzhipeng on 16/8/22.
 */
public abstract class ValueAnimatorProxy<T extends ValueAnimatorProxy> {
    //1. set的都return child;  弄成新包~
    //并且添加一些额外的方法? 这样貌似不能用策略吧  模板比较合适;
    //2. 构造默认的一些初始化
    //3.设置一个Object
    //4.监听的时候调用这个object

    private ValueAnimator source;
    protected T child;

    private void checkChild() {
        if (child == null)
            throw new IllegalStateException("child must be set in child's Constructor!" +
                    "not method:initDefaultValueAnimator");
    }

    public ValueAnimatorProxy() {
        source = initDefaultValueAnimator();
    }

    public abstract ValueAnimator initDefaultValueAnimator();


    public T setTarget(Object target) {
        source.setTarget(target);
        checkChild();
        return child;
    }


    public T setIntValues(int... values) {
        source.setIntValues(values);
        checkChild();
        return child;
    }


    public T setFloatValues(float... values) {
        source.setFloatValues(values);
        checkChild();
        return child;
    }


    public T setObjectValues(Object... values) {
        source.setObjectValues(values);
        checkChild();
        return child;
    }


    public T setValues(PropertyValuesHolder... values) {
        source.setValues(values);
        checkChild();
        return child;
    }


    public PropertyValuesHolder[] getValues() {
        return source.getValues();
    }


    public ValueAnimator setDuration(long duration) {
        return source.setDuration(duration);
    }


    public long getDuration() {
        return source.getDuration();
    }


    public void setCurrentPlayTime(long playTime) {
        source.setCurrentPlayTime(playTime);
    }


    public long getCurrentPlayTime() {
        return source.getCurrentPlayTime();
    }


    public long getStartDelay() {
        return source.getStartDelay();
    }


    public T setStartDelay(long startDelay) {
        source.setStartDelay(startDelay);
        checkChild();
        return child;
    }


    public Object getAnimatedValue() {
        return source.getAnimatedValue();
    }


    public Object getAnimatedValue(String propertyName) {
        return source.getAnimatedValue(propertyName);
    }


    public T setRepeatCount(int value) {
        source.setRepeatCount(value);
        checkChild();
        return child;
    }


    public int getRepeatCount() {
        return source.getRepeatCount();
    }


    public T setRepeatMode(int value) {
        source.setRepeatMode(value);
        checkChild();
        return child;
    }


    public int getRepeatMode() {
        return source.getRepeatMode();
    }


    public T addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        source.addUpdateListener(listener);
        checkChild();
        return child;
    }


    public T removeAllUpdateListeners() {
        source.removeAllUpdateListeners();
        checkChild();
        return child;
    }


    public T removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        source.removeUpdateListener(listener);
        checkChild();
        return child;
    }


    public T setInterpolator(Interpolator value) {
        source.setInterpolator(value);
        checkChild();
        return child;
    }


    public Interpolator getInterpolator() {
        return source.getInterpolator();
    }


    public T setEvaluator(TypeEvaluator value) {
        source.setEvaluator(value);
        checkChild();
        return child;
    }


    public void start() {
        source.start();
    }


    public void cancel() {
        source.cancel();
    }


    public void end() {
        source.end();
    }


    public boolean isRunning() {
        return source.isRunning();
    }


    public boolean isStarted() {
        return source.isStarted();
    }


    public T reverse() {
        source.reverse();
        checkChild();
        return child;
    }


    public float getAnimatedFraction() {
        return source.getAnimatedFraction();
    }


    public ValueAnimator clone() {
        return source.clone();
    }


    public String toString() {
        return source.toString();
    }


    public T addListener(Animator.AnimatorListener listener) {
        source.addListener(listener);
        checkChild();
        return child;
    }


    public T removeListener(Animator.AnimatorListener listener) {
        source.removeListener(listener);
        checkChild();
        return child;
    }


    public ArrayList<Animator.AnimatorListener> getListeners() {
        return source.getListeners();
    }


    public T removeAllListeners() {
        source.removeAllListeners();
        checkChild();
        return child;
    }


    public T setupStartValues() {
        source.setupStartValues();
        checkChild();
        return child;
    }


    public T setupEndValues() {
        source.setupEndValues();
        checkChild();
        return child;
    }

    public ValueAnimator source() {
        return source;
    }
}
