package zone.com.zanimate.object;

import android.view.animation.Interpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fuzhipeng on 16/6/29.
 */
public class AnimatorSetProxy implements Cloneable {
    AnimatorSet mAnimatorSet;


    public AnimatorSetProxy playTogether(Animator... items) {
        mAnimatorSet.playTogether(items);
        return this;
    }

    public AnimatorSetProxy playTogether(Collection<Animator> items) {
        mAnimatorSet.playTogether(items);
        return this;
    }

    public AnimatorSetProxy playSequentially(Animator... items) {
        mAnimatorSet.playSequentially(items);
        return this;
    }

    public AnimatorSetProxy playSequentially(List<Animator> items) {
        mAnimatorSet.playSequentially(items);
        return this;
    }

    public ArrayList<Animator> getChildAnimations() {
        return mAnimatorSet.getChildAnimations();
    }


    public AnimatorSetProxy cancel() {
        mAnimatorSet.cancel();
        return this;
    }

    public AnimatorSetProxy end() {
        mAnimatorSet.end();
        return this;
    }

    public boolean isRunning() {
        return mAnimatorSet.isRunning();
    }

    public boolean isStarted() {
        return mAnimatorSet.isStarted();
    }

    public long getStartDelay() {
        return mAnimatorSet.getStartDelay();
    }

    public long getDuration() {
        return mAnimatorSet.getDuration();
    }

    @Override
    public AnimatorSetProxy clone() {
        return new AnimatorSetProxy(mAnimatorSet.clone());
    }

    public AnimatorSetProxy addListener(Animator.AnimatorListener listener) {
        mAnimatorSet.addListener(listener);
        return this;
    }

    public AnimatorSetProxy removeListener(Animator.AnimatorListener listener) {
        mAnimatorSet.removeListener(listener);
        return this;
    }

    public ArrayList<Animator.AnimatorListener> getListeners() {
        return mAnimatorSet.getListeners();
    }

    public AnimatorSetProxy removeAllListeners() {
        mAnimatorSet.removeAllListeners();
        return this;
    }

    public AnimatorSetProxy(AnimatorSet mAnimatorSet) {
        this.mAnimatorSet = mAnimatorSet;
    }


    //正常是11对应的关系;
    //特殊情况:但是如果不等 并且动画数量大于objs的时候,最后一个objs应用到剩下没有被对应的动画;
    public AnimatorSetProxy setTarget(Object... objs) {
        if (objs.length == 0 || objs == null)
            return null;
        ArrayList<Animator> childs = mAnimatorSet.getChildAnimations();
        for (int i = 0; i < childs.size(); i++) {
            int length = objs.length;
            if (i < length)
                childs.get(i).setTarget(objs[i]);
            else
                childs.get(i).setTarget(objs[length - 1]);
        }
        return this;
    }

    //等比设置 比如8个动画 3个objs,那么123 对应obj1 456对应obj2 78对应obj3
    public AnimatorSetProxy setTargetRatio(Object... objs) {
        if (objs.length == 0 || objs == null)
            return null;
        ArrayList<Animator> childs = mAnimatorSet.getChildAnimations();
        for (int i = 0; i < childs.size(); i++)
            childs.get(i).setTarget(objs[i / objs.length]);
        return this;
    }

    public AnimatorSetProxy setInterpolator(/*Time*/Interpolator interpolator) {
        mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public AnimatorSetProxy setStartDelay(long setStartDelay) {
        mAnimatorSet.setStartDelay(setStartDelay);
        return this;
    }

    public AnimatorSetProxy setupEndValues() {
        mAnimatorSet.setupEndValues();
        return this;
    }

    public AnimatorSetProxy setupStartValues() {
        mAnimatorSet.setupStartValues();
        return this;
    }

    //如果这个设置了 那么所有的 动画全被被迭代设置; 可以进去看看
    public AnimatorSetProxy setDuration(long duration) {
        mAnimatorSet.setDuration(duration);
        return this;
    }

    public AnimatorSetProxy start() {
        mAnimatorSet.start();
        return this;
    }

    public AnimatorSet source() {
        return mAnimatorSet;
    }

}
