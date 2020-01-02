package zone.com.zanimate.object;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.animation.Interpolator;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;

import java.util.ArrayList;

/**
 * Created by fuzhipeng on 16/8/23.
 * 仅仅借用了ObjectAnimator source
 */
public class ObjectAnimatorProxy  implements Cloneable {
    private ObjectAnimator source;

    public ObjectAnimatorProxy() {
        source = new ObjectAnimator();
    }
    private ObjectAnimatorProxy(ObjectAnimator source) {
        this.source =source;
    }

    
    public PropertyValuesHolder[] getValues() {
        return source.getValues();
    }

    
    public long getDuration() {
        return source.getDuration();
    }

    public ObjectAnimatorProxy setCurrentPlayTime(long playTime) {
        source.setCurrentPlayTime(playTime);
        return this;
    }

    public long getCurrentPlayTime() {
        return source.getCurrentPlayTime();
    }

    public long getStartDelay() {
        return source.getStartDelay();
    }

    public Object getAnimatedValue() {
        return source.getAnimatedValue();
    }

    public Object getAnimatedValue(String propertyName) {
        return source.getAnimatedValue(propertyName);
    }

    public int getRepeatCount() {
        return source.getRepeatCount();
    }

    public int getRepeatMode() {
        return source.getRepeatMode();
    }

    public TimeInterpolator getInterpolator() {
        return source.getInterpolator();
    }

    public ObjectAnimatorProxy cancel() {
        source.cancel();
        return this;
    }

    public ObjectAnimatorProxy end() {
        source.end();
        return this;
    }

    public boolean isRunning() {
        return source.isRunning();
    }

    public boolean isStarted() {
        return source.isStarted();
    }

    public float getAnimatedFraction() {
        return source.getAnimatedFraction();
    }

    public String toString() {
        return source.toString();
    }

    public ObjectAnimatorProxy addListener(Animator.AnimatorListener listener) {
        source.addListener(listener);
        return this;
    }

    public ObjectAnimatorProxy removeListener(Animator.AnimatorListener listener) {
        source.removeListener(listener);
        return this;
    }

    public ArrayList<Animator.AnimatorListener> getListeners() {
        return source.getListeners();
    }

    public ObjectAnimatorProxy removeAllListeners() {
        source.removeAllListeners();
        return this;
    }

    // ---------------------ValueAnimatorProxy-----------------------------
    public ObjectAnimatorProxy setIntValues(int... values) {
        source.setIntValues(values);
        return this;
    }


    public ObjectAnimatorProxy setFloatValues(float... values) {
        source.setFloatValues(values);
        return this;
    }


    public ObjectAnimatorProxy setValues(PropertyValuesHolder... values) {
        source.setValues(values);
        return this;
    }


    public ObjectAnimatorProxy setObjectValues(Object... values) {
        source.setObjectValues(values);
        return this;
    }


    public ObjectAnimatorProxy setDuration(long duration) {
        source.setDuration(duration);
        return this;
    }


    public ObjectAnimatorProxy setStartDelay(long startDelay) {
        source.setStartDelay(startDelay);
        return this;
    }


    /**
     *
     * @param value   * @param value {@link ValueAnimator#INFINITE}
     * @return
     */
    public ObjectAnimatorProxy setRepeatCount(int value) {
        source.setRepeatCount(value);
        return this;
    }


    public ObjectAnimatorProxy setEvaluator(TypeEvaluator value) {
        source.setEvaluator(value);
        return this;
    }


    public ObjectAnimatorProxy removeAllUpdateListeners() {
        source.removeAllUpdateListeners();
        return this;
    }


    public ObjectAnimatorProxy removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        source.removeUpdateListener(listener);
        return this;
    }


    public ObjectAnimatorProxy setInterpolator(Interpolator value) {
        source.setInterpolator(value);
        return this;
    }


    //会调用start并 反转动画 但是start调用的却不是翻转;
    public ObjectAnimatorProxy reverse() {
        source.reverse();
        return this;
    }

    /**
     * @param value {@link ValueAnimator#REVERSE}{@link ValueAnimator#RESTART}
     * @return
     */
    public ObjectAnimatorProxy setRepeatMode(int value) {
        source.setRepeatMode(value);
        return this;
    }


    public ObjectAnimatorProxy addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        source.addUpdateListener(listener);
        return this;
    }


    public ObjectAnimatorProxy setTarget(Object target) {
        source.setTarget(target);
        return this;
    }


    public ObjectAnimatorProxy setupStartValues() {
        source.setupStartValues();
        return this;
    }


    public ObjectAnimatorProxy setupEndValues() {
        source.setupEndValues();
        return this;
    }

    public ObjectAnimatorProxy start(){
        source.start();
        return this;
    }

    // ---------------------ObjectAnimator-----------------------------

    public ObjectAnimatorProxy setProperty(Property property) {
        source.setProperty(property);
        return this;
    }

    public ObjectAnimatorProxy setPropertyName(String propertyName) {
        source.setPropertyName(propertyName);
        return this;
    }

    public ObjectAnimator source() {
        return source;
    }

    @Override
    public ObjectAnimatorProxy clone() {
        ObjectAnimatorProxy clone = new ObjectAnimatorProxy();
        clone.source = this.source.clone();
        return clone;
    }

    public static ObjectAnimatorProxy ofFloat(String propertyName, float... values) {
        return new ObjectAnimatorProxy(ObjectAnimator.ofFloat(null, propertyName, values));
    }

    public static ObjectAnimatorProxy ofFloat(Object target, String propertyName, float... values) {
        return new ObjectAnimatorProxy( ObjectAnimator.ofFloat(target, propertyName, values));
    }


    public static ObjectAnimatorProxy ofInt(String propertyName, int... values) {
        return new ObjectAnimatorProxy( ObjectAnimator.ofInt(null, propertyName, values));
    }

    public static  ObjectAnimatorProxy ofInt(Object target, String propertyName, int... values) {
        return  new ObjectAnimatorProxy(ObjectAnimator.ofInt(target, propertyName, values));
    }

    public static ObjectAnimatorProxy ofObject(String propertyName, TypeEvaluator evaluator, Object... values) {
        return new ObjectAnimatorProxy(ObjectAnimator.ofObject(null, propertyName, evaluator, values));
    }

    public static ObjectAnimatorProxy ofObject(Object target, String propertyName, TypeEvaluator evaluator, Object... values) {
        return new ObjectAnimatorProxy(ObjectAnimator.ofObject(target, propertyName, evaluator, values));
    }
}
