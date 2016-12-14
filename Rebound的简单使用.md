# Rebound的简单使用与源码分析
>综述：Rebound 通过胡克定律，实现的一个类似“弹簧”动画效果的第三方工具包。

[官方demo摘出](https://github.com/luhaoaimama1/ReboundDemo)

###单独使用
```
Spring spring =  SpringSystem.create().createSpring();
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
spring.setEndValue(1);//默认endValue是0；          
```

>Ps:SpringUtil.mapValueFromRangeToRange(currentValue, 0, 1, 1, 0.5);

> 是映射工具类 0->1的转映射到1->0.5

###监听
>SimpleSpringListener实现了SpringListener

```
public interface SpringListener {
  // 在首次开始运动时候调用。
  void onSpringActivate(Spring spring);
  //在advance后调用，表示状态更新。
  void onSpringUpdate(Spring spring);
  //在进入rest状态后调用。
  void onSpringAtRest(Spring spring);
  //则略有不同，仅在setEndValue中被调用，且该Spring需要在运动中且新的endValue不等于原endValue。
  void onSpringEndStateChange(Spring spring);
}
```

###配置
>拉力(tension) default:40--->拉力越大，弹簧效果越明显

>摩擦力(friction) default:7-->弹框效果阻力越大、越不明显

>Ps:如果这个摩擦力的值设置为0，就像真实世界中处于真空状态，一点摩擦力都没有，这个弹簧效果会一直无限制重复下去，根本停不下来

```
  spring.setSpringConfig(new SpringConfig(tensionValue, frictionValue));
```

###setEndValue与setCurrentValue的区别 
>end是一个值变化的过程,currentValue是表示插入一个值，表示一个瞬间(因为reset了~)；

>Ps:setAtRest()会让他停止动画；

![](./demo/rebound区别.png)




###连锁动画

```
SpringChain mSpringChain = SpringChain.create();
mSpringChain.addSpring(new SimpleSpringListener(){});
mSpringChain.setControlSpringIndex(2).getControlSpring().setEndValue(0);
```
* SpringChain这个类，创建它有两个create方法：
    * 默认无参数create()
    * 有参数的create(int mainTension,int mainFriction,int attachmentTension,int attachmentFriction)
        >mainTension:主导spring的拉力系数，mainFriction:主导Spring的摩擦力系数，
        
        >attachmentTensio,attachmentFriction:表示附属的拉力和摩擦力系数
        
>SpringChain需要设置一个起主导控制作用的Spring，通过setControlSpringIndex方法来设置

>getControlSpring()则代表 获得主导的Spring

>addSpring 里其实直接new了.  通过mSpringChain.getAllSprings()可以获取到注册到mSpringChain里的所有Spring

![](./demo/rebound_addSpring.png)

###官方小球demo解析
    
* setVelocity
    >会根据当前的速度计算剩下的位置；类似setEndValue 是一个值的变化 而不是瞬间；

    >Ps:根据官方小球demo,setVelocity之前 会把拉力设置为0 可能是为了拉力不影响速度的值吧；
    
    >Tip:setEndValue 则是和拉力有关系没速度没关系。所以下面代码在setEndValue使用之前 把拉力从0改成20

* SpringSystemListener（SpringSystem.addListener）
    * onBeforeIntegrate 每次计算之前
    * onAfterIntegrate  每次计算之后

>为什么在onAfterIntegrate里写 而不是onBeforeIntegrate 因为如果出界后可以马上放回来。

```
springSystem = SpringSystem.create();
springSystem.addListener(this);
xSpring = springSystem.createSpring();
ySpring = springSystem.createSpring();

@Override
public void onBeforeIntegrate(BaseSpringSystem springSystem) {
}

@Override
public void onAfterIntegrate(BaseSpringSystem springSystem) {
    //x就不写了 同y
    if (y + radius >= getHeight()) {
        //球碰到或出边界  吧速度返过来 然后setCurrentValue注意后边有个false 是让动画继续的意思；
       ySpring.setVelocity(-ySpring.getVelocity());
        //ySpring.getCurrentValue() - (y + radius - getHeight()) 表示如果出界 就把他放回界内；
       ySpring.setCurrentValue(ySpring.getCurrentValue() - (y + radius - getHeight()), false);
     }
     if (y - radius <= 0) {
        //同上
       ySpring.setVelocity(-ySpring.getVelocity());
       ySpring.setCurrentValue(ySpring.getCurrentValue() - (y - radius), false);
     }
 
     for (PointF point : points) {
       if (dist(x, y, point.x, point.y) < attractionThreshold &&
           Math.abs(xSpring.getVelocity()) < 900 &&
           Math.abs(ySpring.getVelocity()) < 900 &&
           !dragging) {
           //如果速度小于某个值 并且在某个圆的一定范围内 并且手没有拖拽,则用setEndValue平滑过渡到某一点；
         xSpring.setSpringConfig(CONVERGING);
         xSpring.setEndValue(point.x);
         //CONVERGING=SpringConfig.fromOrigamiTensionAndFriction(20, 3); 
         //这里为什么把拉力弄成20？因为setEndValue和速度没关系和拉力有关系被；
         ySpring.setSpringConfig(CONVERGING);
         ySpring.setEndValue(point.y);
       }
     }
}
```

###简单的源码流程分析图
![](./demo/rebound简单的源码流程.png)

# Reference&Thanks：

http://blog.csdn.net/hanhailong726188/article/details/50687466

http://www.jianshu.com/p/9d56d92d337c