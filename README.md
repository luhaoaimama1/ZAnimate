# ZAnimate
>本README 主要介绍2D库；

一个简约的动画封装工具类库。

目的:使用更简单,更优雅~

`2D库的优点`:  NineOldAndroids的 `链接调用` `增加预设` `去掉重复参数`
>Tip:`链接调用碰到继承改如何使用` [解决参考](https://github.com/luhaoaimama1/ZAnimate/blob/master/app/src/main/java/zone/com/zanimate/ViewWrap.java) [我的扫光库也使用到了](https://github.com/luhaoaimama1/Shine/blob/master/lightsweep/src/main/java/zone/com/lightsweep/ShineAnimator.java)

`3D库的使用介绍`: [Camera的研究与封装](https://luhaoaimama1.github.io/2017/01/23/Camera调研/)

`Rebound 弹簧系统-让动画不再僵硬`：[Rebound的简单使用与源码分析](Rebound的简单使用.md)
# 运行环境
Android Studio  3.5

### JicPack
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Step 2. Add the dependency

> compile 'com.github.luhaoaimama1:ZAnimate:[Latest release](https://github.com/luhaoaimama1/ZAnimate/releases)'
> 非androidX分支 compile 'com.github.luhaoaimama1:ZAnimate:1.0.21' 分支：notAndroidX

    
    


##ObjectAnimatorHelper的四种使用方式:

###first:
ofFloat,ofInt,ofObject,支持我写的代理额类则繁衍了,ofFloatProxy,ofIntProxy,ofObjectProxy;范例:

    ObjectAnimatorHelper.ofFloatProxy(tv, "translationX", 0, 300).setStartDelay(1000).start();

###second:
playSequentially,playTogether.为了支持本身的代理类繁衍了playSequentiallyProxy,playTogetherProxy,

       //封装动画
        ObjectAnimatorHelper.playTogether(
                ObjectAnimatorHelper.ofFloat("alpha", 1F, 0.5F, 1F),
                ObjectAnimatorHelper.ofFloat("scaleX", 1F, 0.8F, 1F),
                ObjectAnimatorHelper.ofFloat("scaleY", 1F, 0.8F, 1F)
        ).setTarget(tv, tv2).setDuration(600).start();

setTarget(在此类中AnimatorSetProxy )两种:

    First:
    //正常是11对应的关系;
    //特殊情况:但是如果不等 并且动画数量大于objs的时候,最后一个objs应用到剩下没有被对应的动画;
    setTarget(Object... objs)
    
    Second:
    //等比设置 比如8个动画 3个objs,那么123 对应obj1 456对应obj2 78对应obj3
    setTargetRatio(Object... objs)

### third:
序列动画 play 对应的类是SortAnimator;
>请注意:with是伴随 play或者After开始的

>但是after是前一个play结束开始的 而和with结束没关系;

       // 特定顺序的动画
           ObjectAnimatorHelper
                   .play(ObjectAnimatorHelper.ofFloatProxy( "translationX", 0F, 300F).setStartDelay(1000))
                   .with(ObjectAnimatorHelper.ofFloatProxy("alpha", 1F, 0.5F, 1F).setStartDelay(1000))//这个应该第一个after播放
                   .after(ObjectAnimatorHelper.ofFloat( "translationY", 0F, 300F))
                   .after(ObjectAnimatorHelper.ofFloat("translationX", 300F, 0F))
                   .after(ObjectAnimatorHelper.ofFloat( "translationY", 300F, 0F),1000)
                   .with(ObjectAnimatorHelper.ofFloat("alpha", 1F, 0.5F, 1F))
                   .setTarget(tv)
                   .setDuration(1000)
                   .start();

####请注意!!!
>这个和原生的`逻辑`不一样，虽然名字一样;

>原生的逻辑-——>after在前边,play在after 300以后播放,with和play一起播放, before最后播放;

>而且使用起来各种意义上的迷糊现在也没弄懂.所以自己造了一个~;

### four:
预设动画playPreset(平时觉得好的可以 可以用此存起来) 对应的接口 BaseViewAnimator,范例类;ExampleAnimator,范例:

    ObjectAnimatorHelper.playPreset(ExampleAnimator.class).setTarget(tv).start();

##ValueAnimatorProxy的使用方式:
ValueAnimator 用ValueAnimatorProxy;
>默认插值器：AccelerateDecelerateInterpolator(by:androidnineold）

```
    valueAnimator = ValueAnimatorProxy.ofInt(0, 100)
            .setRepeatCount(ValueAnimator.INFINITE)
            .setDuration(1500)
            .setRepeatMode(ValueAnimator.REVERSE)
            .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mProgressBar.setProgress((Integer) animation.getAnimatedValue());
                }
            }).start();
```

##最后请注意:
动画的两种监听(1.addUpdateListener 2.addListener)是 list收集的  所以多次add不会替换掉;


# Reference&Thanks：

https://github.com/JakeWharton/NineOldAndroids

https://github.com/daimajia/AndroidImageSlider