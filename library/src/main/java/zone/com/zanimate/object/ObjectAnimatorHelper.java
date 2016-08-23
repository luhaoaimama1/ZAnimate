package zone.com.zanimate.object;
import android.view.View;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.view.ViewHelper;
import java.util.List;

/**
 * Created by zone on 2016/6/27.
 * 特定顺序：ObjectAnimatorHelper.newInstance().play(bounceAnim).before(squashAnim1).with(stretchAnim2).after(stretchAnim2);
 */
public class ObjectAnimatorHelper {
    public static long DURATION = 800;

    private static AnimatorSet newInstance() {
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(DURATION);
        return mAnimatorSet;
    }

    public static AnimatorSetProxy playSequentially(Animator... items) {
        AnimatorSet mAnimatorSet = newInstance();
        mAnimatorSet.playSequentially(items);
        return new AnimatorSetProxy(mAnimatorSet);
    }
    public static AnimatorSetProxy playSequentiallyProxy(ObjectAnimatorProxy... items) {
        Animator[] aniArr =getAniArr(items);
        return playSequentially(aniArr);
    }

    private static Animator[] getAniArr(ObjectAnimatorProxy[] items) {
        Animator[] aniArr = new Animator[items.length];
        for (int i = 0; i < items.length; i++)
            aniArr[i]=items[i].source();
        return aniArr;
    }
    private static Animator[] getAniArr(List<ObjectAnimatorProxy> items) {
        Animator[] aniArr = new Animator[items.size()];
        for (int i = 0; i < items.size(); i++)
            aniArr[i]=items.get(i).source();
        return aniArr;
    }

    public static AnimatorSetProxy playSequentially(List<Animator> items) {
        AnimatorSet mAnimatorSet = newInstance();
        mAnimatorSet.playSequentially(items);
        return new AnimatorSetProxy(mAnimatorSet);
    }
    public static AnimatorSetProxy playSequentiallyProxy(List<ObjectAnimatorProxy> items) {
        Animator[] aniArr =getAniArr(items);
        return playSequentially(aniArr);
    }

    public static AnimatorSetProxy playTogether(Animator... items) {
        AnimatorSet mAnimatorSet = newInstance();
        mAnimatorSet.playTogether(items);
        return new AnimatorSetProxy(mAnimatorSet);
    }
    public static AnimatorSetProxy playTogetherProxy(ObjectAnimatorProxy... items) {
        Animator[] aniArr =getAniArr(items);
        return playTogether(aniArr);
    }


    public static AnimatorSetProxy playTogether(List<Animator> items) {
        AnimatorSet mAnimatorSet = newInstance();
        mAnimatorSet.playTogether(items);
        return new AnimatorSetProxy(mAnimatorSet);
    }
    public static AnimatorSetProxy playTogetherProxy(List<ObjectAnimatorProxy> items) {
        Animator[] aniArr =getAniArr(items);
        return playTogether(aniArr);
    }


    public static AnimatorSetProxy playPreset(BaseViewAnimator mBaseViewAnimator) {
        AnimatorSet mAnimatorSet = newInstance();
        mBaseViewAnimator.prepare(mAnimatorSet);
        return new AnimatorSetProxy(mAnimatorSet);
    }



    public static AnimatorSetProxy playPreset(Class<? extends BaseViewAnimator> mBaseViewAnimator) {
        try {
            return playPreset(mBaseViewAnimator.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObjectAnimator ofFloat(String propertyName, float... values) {
        return ObjectAnimator.ofFloat(null, propertyName, values);
    }


    public static ObjectAnimator ofFloat(Object target, String propertyName, float... values) {
        return ObjectAnimator.ofFloat(target, propertyName, values);
    }

    public static ObjectAnimatorProxy ofFloatProxy(String propertyName, float... values) {
        return ObjectAnimatorProxy.ofFloat(null, propertyName, values);
    }

    public static ObjectAnimatorProxy ofFloatProxy(Object target, String propertyName, float... values) {
        return ObjectAnimatorProxy.ofFloat(target, propertyName, values);
    }


    public static ObjectAnimator ofInt(String propertyName, int... values) {
        return ObjectAnimator.ofInt(null, propertyName, values);
    }

    public static ObjectAnimator ofInt(Object target, String propertyName, int... values) {
        return ObjectAnimator.ofInt(target, propertyName, values);
    }
    public static ObjectAnimatorProxy ofIntProxy(String propertyName, int... values) {
        return ObjectAnimatorProxy.ofInt(null, propertyName, values);
    }

    public static ObjectAnimatorProxy ofIntProxy(Object target, String propertyName, int... values) {
        return ObjectAnimatorProxy.ofInt(target, propertyName, values);
    }

    public static ObjectAnimator ofObject(String propertyName, TypeEvaluator evaluator, Object... values) {
        return ObjectAnimator.ofObject(null, propertyName, evaluator, values);
    }

    public static ObjectAnimator ofObject(Object target, String propertyName, TypeEvaluator evaluator, Object... values) {
        return ObjectAnimator.ofObject(target, propertyName, evaluator, values);
    }
    public static ObjectAnimatorProxy ofObjectProxy(String propertyName, TypeEvaluator evaluator, Object... values) {
        return ObjectAnimatorProxy.ofObject(null, propertyName, evaluator, values);
    }

    public static ObjectAnimatorProxy ofObjectProxy(Object target, String propertyName, TypeEvaluator evaluator, Object... values) {
        return ObjectAnimatorProxy.ofObject(target, propertyName, evaluator, values);
    }

    // 特定顺序的动画   不需要设置时间
    // ObjectAnimatorHelper.play(bounceAnim).before(squashAnim1).with(stretchAnim2).after(stretchAnim2).start();
    public static SortAnimator play(Animator mAnimator) {
        SortAnimator result = new SortAnimator();
        result.play(mAnimator);
        return result;
    }
    // 特定顺序的动画   不需要设置时间
    // ObjectAnimatorHelper.play(bounceAnim).before(squashAnim1).with(stretchAnim2).after(stretchAnim2).start();
    public static SortAnimator play(ObjectAnimatorProxy mAnimator) {
        return play(mAnimator.source());
    }

//    PROXY_PROPERTIES.put("alpha", PreHoneycombCompat.ALPHA);
//    PROXY_PROPERTIES.put("pivotX", PreHoneycombCompat.PIVOT_X);
//    PROXY_PROPERTIES.put("pivotY", PreHoneycombCompat.PIVOT_Y);
//    PROXY_PROPERTIES.put("translationX", PreHoneycombCompat.TRANSLATION_X);
//    PROXY_PROPERTIES.put("translationY", PreHoneycombCompat.TRANSLATION_Y);
//    PROXY_PROPERTIES.put("rotation", PreHoneycombCompat.ROTATION);
//    PROXY_PROPERTIES.put("rotationX", PreHoneycombCompat.ROTATION_X);
//    PROXY_PROPERTIES.put("rotationY", PreHoneycombCompat.ROTATION_Y);
//    PROXY_PROPERTIES.put("scaleX", PreHoneycombCompat.SCALE_X);
//    PROXY_PROPERTIES.put("scaleY", PreHoneycombCompat.SCALE_Y);
//    PROXY_PROPERTIES.put("scrollX", PreHoneycombCompat.SCROLL_X);
//    PROXY_PROPERTIES.put("scrollY", PreHoneycombCompat.SCROLL_Y);
//    PROXY_PROPERTIES.put("x", PreHoneycombCompat.X);
//    PROXY_PROPERTIES.put("y", PreHoneycombCompat.Y);
    public static void reset(View target) {
        ViewHelper.setAlpha(target, 1);//字符串就是alpha set去掉第一个字母在变成小写;
        ViewHelper.setScaleX(target, 1);
        ViewHelper.setScaleY(target, 1);
        ViewHelper.setTranslationX(target, 0);
        ViewHelper.setTranslationY(target, 0);
        ViewHelper.setRotation(target, 0);
        ViewHelper.setRotationY(target, 0);
        ViewHelper.setRotationX(target, 0);
        ViewHelper.setPivotX(target, target.getMeasuredWidth() / 2.0f);
        ViewHelper.setPivotY(target, target.getMeasuredHeight() / 2.0f);
    }

    //为了添加预设而用  //灵感来自 daimajia的AndroidViewAnimations
    public interface BaseViewAnimator {
        void prepare(AnimatorSet mAnimatorSet);
    }
}
