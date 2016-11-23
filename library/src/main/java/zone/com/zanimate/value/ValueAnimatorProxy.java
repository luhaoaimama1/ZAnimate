package zone.com.zanimate.value;

import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by fuzhipeng on 16/8/23.
 */
public class ValueAnimatorProxy extends ValueAnimatorProxyAbstract<ValueAnimatorProxy> {

    public ValueAnimatorProxy() {
        child = this;//一定要设置 没设置到时候会抛异常给你;
    }

    @Override
    public ValueAnimator initDefaultValueAnimator() {
        return new ValueAnimator();
    }


    public static ValueAnimatorProxy ofFloat(float... values) {
        ValueAnimatorProxy result = new ValueAnimatorProxy();
        result.setSource(ValueAnimator.ofFloat(values));
        return result;
    }

    public static ValueAnimatorProxy ofInt(int... values) {
        ValueAnimatorProxy result = new ValueAnimatorProxy();
        result.setSource(ValueAnimator.ofInt(values));
        return result;
    }

    public static ValueAnimatorProxy ofObject(TypeEvaluator evaluator, Object... values) {
        ValueAnimatorProxy result = new ValueAnimatorProxy();
        result.setSource(ValueAnimator.ofObject(evaluator, values));
        return result;
    }

    public static ValueAnimatorProxy ofPropertyValuesHolder(PropertyValuesHolder... values) {
        ValueAnimatorProxy result = new ValueAnimatorProxy();
        result.setSource(ValueAnimator.ofPropertyValuesHolder(values));
        return result;
    }
}
