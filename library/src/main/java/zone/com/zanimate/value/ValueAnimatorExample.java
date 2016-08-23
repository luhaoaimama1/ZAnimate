package zone.com.zanimate.value;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by fuzhipeng on 16/8/22.
 */
public class ValueAnimatorExample extends ValueAnimatorProxy<ValueAnimatorExample> {

    public ValueAnimatorExample() {
        child=this;//一定要设置 没设置到时候会抛异常给你;
    }

    @Override
    public ValueAnimator initDefaultValueAnimator() {
        return  ValueAnimator.ofFloat(0F, 1F);
    }

    //这个就是测试 看看返回是不是Example 而不是  父类;
    @Override
    public ValueAnimatorExample setRepeatCount(int value) {
        return super.setRepeatCount(value);
    }
}
