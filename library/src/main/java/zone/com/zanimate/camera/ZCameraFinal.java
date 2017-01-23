package zone.com.zanimate.camera;

import android.graphics.Camera;
import android.graphics.Matrix;

/**
 * Created by fuzhipeng on 16/9/15.
 *
 *  更正  Camera 坐标系; 为左手坐标系  和view 动画相同；同时左手坐标系 并且完全准守;
 *
 *  图层在旋转与位移 原因:因为location 没有因为旋转和位移更改位置;
 */
public class ZCameraFinal extends Camera {
    private final int layerWidth,  layerHeight;
    private float px,py;
    private PivotType pivotType=PivotType.None;

    public ZCameraFinal(int layerWidth, int layerHeight) {
        this.layerWidth=layerWidth;
        this.layerHeight=layerHeight;
    }

    @Override
    public void translate(float x, float y, float z) {
        super.translate(x, -y, -z);
    }


    @Override
    public void rotate(float x, float y, float z) {
        super.rotate(x, y, -z);
    }


    @Override
    public void rotateZ(float deg) {
        super.rotateZ(-deg);
    }
    //注意 set此以后 坐标系更改 translate也会因为他改变而改变额  因为又pre操作
    public ZCameraFinal setPivot(float px, float py) {
        this.px = px;
        this.py = py;
        return this;
    }
//    //todo
//    public ZCameraFinal setPivot(PivotType pivotType) {
//        this.pivotType = pivotType;
//        if ()
//    return this;
//    }

    @Override
    public void getMatrix(Matrix matrix) {
        super.getMatrix(matrix);
        matrix.preTranslate(-px,-py);
        matrix.postTranslate(px,py);
    }
    /**
     * 0---1---2
     * |       |
     * 7   8   3
     * |       |
     * 6---5---4
     */


    public enum PivotType{
        None,
        LeftTop,LeftCenter,LeftBottom,
        RightTop,RightCenter,RightBottom,
        CenterTop,Center,CenterBottom,
    }


    /**
     *
     * @param parent
     */
    public void BindingParent(ZCameraFinal parent){

    }
}
