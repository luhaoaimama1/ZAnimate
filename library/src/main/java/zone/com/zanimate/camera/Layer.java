package zone.com.zanimate.camera;

import android.graphics.Matrix;

/**
 * Created by Administrator on 2016/9/16.
 * <p>
 * rotation by parent x,y,z
 * 坐标轴都参考 CameraInvert 类；
 */
public class Layer {

//    设计规范 layer.setPoivet.relativeZPosition.ro.ro.ro.atttch（parent(location).ro.ro）


//    设计规范 layer.setPoivet(x,y,z).position(x,y,z).ro.ro.ro.atttch（parent(location).ro.r
    //


    private float z;
    private float px, py;
    private float rX_Degrees, rY_Degrees, rZ_Degrees;
    private Matrix mMatrix;

    private Layer() {
    }

    /**
     * @param z 可以正负  范例:如果 relativeZPosition(-400) 并且 parent (100,200,-500)
     *          那么我的位置就是 （100,200,-900=[parent.z+this.z]）
     * @return
     */
    public Layer relativeZPosition(float z) {
        this.z = z;
        return this;
    }

    /**
     * 旋转所围绕的点；
     *
     * @param px
     * @param py
     * @return
     */
    public static Layer setPivot(float px, float py) {
        Layer layer = new Layer();
        layer.px = px;
        layer.py = py;
        return layer;
    }

    public Layer rotationX(float degrees) {
        rX_Degrees = degrees;
        return this;
    }

    public Layer rotationY(float degrees) {
        rY_Degrees = degrees;
        return this;

    }

    public Layer rotationZ(float degrees) {
        rZ_Degrees = degrees;
        return this;
    }

    public Matrix attach(LayerParent parent) {
        /**
         *todo 理解:很奇怪  rotate的旋转点  是 之后的translate；
         *如果translate在rotate之前 则是正常；
         *如果 rotate之后没有translate 则相当于translate（0，0，0）
         */
        mMatrix = new Matrix();
        CameraInvert cameraInvert = new CameraInvert(new ZCamera());

        cameraInvert.setPivot(px, py);
        //坐标系平移
        cameraInvert.translate_3D(-px + parent.x, -py + parent.y, parent.z);
//        cameraInvert.translate_3D(parent.x,parent.y, parent.z);
        //围绕 parent 旋转；
        cameraInvert.rotateX_3D(parent.rX_Degrees);//更改  2D旋转(不能放3D前面  不然变得跟平面一样)  为啥会变成这样呢 因为都是操作 Matrix, Matrix 是2D的； 那么你更改X旋转即可； matrix没有 所以用camera操作；
        cameraInvert.rotateY_3D(parent.rY_Degrees);//更改  2D旋转(不能放3D前面  不然变得跟平面一样)  为啥会变成这样呢 因为都是操作 Matrix, Matrix 是2D的； 那么你更改X旋转即可； matrix没有 所以用camera操作；
        cameraInvert.rotateZ_3D(parent.rZ_Degrees);//更改  2D旋转(不能放3D前面  不然变得跟平面一样)  为啥会变成这样呢 因为都是操作 Matrix, Matrix 是2D的； 那么你更改X旋转即可； matrix没有 所以用camera操作；

        //parent 旋转 围绕 parent.z   真实意思是  围绕 0,0,0点半径为 parent.z/2旋转;
        cameraInvert.translate_3D(0, 0, z);//关键性技术:   上面的旋转 是围绕这个点   之后就是围绕自己旋转;

        //本身环绕
        cameraInvert.rotateX_3D(rX_Degrees);
        cameraInvert.rotateY_3D(rY_Degrees);
        cameraInvert.rotateZ_3D(rZ_Degrees);

        cameraInvert.postConcat(mMatrix);

        return mMatrix;
    }

}
