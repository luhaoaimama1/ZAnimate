package zone.com.zanimate.object;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 16/8/23.
 */
public class SortAnimator {
    private List<AnimatorWithIndex> sortList;
    boolean cancel = false;


    public SortAnimator play(Animator anim) {
        sortList = new ArrayList<>();
        sortList.add(new AnimatorWithIndex(anim, 0));
        return this;
    }


    public SortAnimator play(ObjectAnimatorProxy anim) {
        return play(anim.source());
    }

    public SortAnimator with(Animator anim) {
        int lastIndex = getLastAnimatorWithIndex().index;
        AnimatorWithIndex animatorWithIndex = new AnimatorWithIndex(anim, lastIndex);
        animatorWithIndex.isWith = true;
        sortList.add(animatorWithIndex);
        return this;
    }

    public SortAnimator with(ObjectAnimatorProxy anim) {
        return with(anim.source());
    }

    public SortAnimator after(Animator anim) {
        return after(anim, 0);
    }

    public SortAnimator after(ObjectAnimatorProxy anim) {
        return after(anim.source(), 0);
    }

    public SortAnimator start() {
        checkSortBuilder();
        cancel = false;
        AnimatorWithIndex animatorWithIndex = sortList.get(0);
        animatorWithIndex.animator.addListener(listener);
        animatorWithIndex.animator.start();

        return this;
    }

    //play after 添加坚挺  with不需要
    private AnimatorListenerAdapter listener = new AnimatorListenerAdapter() {

        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            //with
            AnimatorWithIndex obj = null;
            for (int i = 0; i < sortList.size(); i++) {
                AnimatorWithIndex item = sortList.get(i);
                if (obj != null && item.index == obj.index) {
                    item.animator.start();
                }
                //找到当前的位置;
                if (animation == item.animator) {
                    obj = item;
                }
            }

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            runAfter(animation);

        }

        //after
        private void runAfter(Animator animation) {
            animation.removeListener(this);//保证  每个animation runAfter仅仅执行一次;
            int index = -1;
            for (int i = 0; i < sortList.size(); i++) {
                if (animation == sortList.get(i).animator) {
                    if (!sortList.get(i).isWith)
                        //找到当前结束并且不是  with类型的 的animation的index;
                        index = sortList.get(i).index;
                }
                if(!cancel){//没有主动退出的时候 找下一个动画

                    //如果play结束找到其位置后，那么找到after动画 并执行
                    AnimatorWithIndex item = sortList.get(i);
                    if ( index != -1 && item.index == (index + 1)) {
                        item.animator.addListener(listener);
                        item.animator.start();
                        break;
                    }
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            super.onAnimationRepeat(animation);
            runAfter(animation);
        }

    };

    public SortAnimator after(Animator anim, long delay) {
        int lastIndex = getLastAnimatorWithIndex().index;
        sortList.add(new AnimatorWithIndex(anim, lastIndex + 1, delay));
        return this;
    }

    //如果这个设置了 那么所有的 动画全被被迭代设置; 可以进去看看
    public SortAnimator setDuration(long duration) {
        checkSortBuilder();
        for (AnimatorWithIndex animatorWithIndex : sortList)
            animatorWithIndex.animator.setDuration(duration);
        return this;
    }

    public SortAnimator setTarget(Object obj) {
        checkSortBuilder();
        for (AnimatorWithIndex animatorWithIndex : sortList)
            animatorWithIndex.animator.setTarget(obj);
        return this;
    }

    public boolean isRunning() {
        boolean isRunning=false;
        for (AnimatorWithIndex animatorWithIndex : sortList)
            if(animatorWithIndex.animator.isRunning())
                isRunning=true;
        return isRunning;
    }

    public SortAnimator cancel() {
        cancel = true;
        for (AnimatorWithIndex animatorWithIndex : sortList) {
            if (animatorWithIndex.animator.isRunning())
                animatorWithIndex.animator.cancel();
        }
        return this;
    }

    private AnimatorWithIndex getLastAnimatorWithIndex() {
        checkSortBuilder();
        return sortList.get(sortList.size() - 1);
    }

    private void checkSortBuilder() {
        if (sortList == null)
            throw new IllegalStateException("method:with,after. first must use method:play!");
    }

    class AnimatorWithIndex {
        Animator animator;
        int index;
        long delay;
        boolean isWith;

        public AnimatorWithIndex(Animator animator, int index) {
            this(animator, index, 0);
        }

        public AnimatorWithIndex(Animator animator, int index, long delay) {
            this.animator = animator;
            this.index = index;
            this.delay = animator.getStartDelay() + delay;
            animator.setStartDelay(this.delay);
//            System.out.println("index:" + this.index + "___delay:" + this.delay);
        }
    }
}
