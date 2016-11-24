package zone.com.zanimate.camera;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;

/**
 * Created by fuzhipeng on 16/9/15.
 * <p>
 * <p>
 * 都是已 左手坐标系为基准（旋转，位移） ，x右边，y下，通过左手坐标系判断，Z就知道了  向外，非朝向手机里面；
 *
 * 摄像机反求类，你把 位移，旋转都给了，他会帮你反求出camera；
 */
public class CameraInvert {

    private Camera mCamera;

    public CameraInvert(ZCamera mCamera) {
        this.mCamera = mCamera;
    }

    public void save() {
        mCamera.save();
    }

    public void restore() {
        mCamera.restore();
    }

    public Camera camera() {
        return mCamera;
    }


    //注意 set此以后 坐标系更改 translate也会因为他改变而改变额  因为又pre操作
    public CameraInvert setPivot(float px, float py) {
        this.px = px;
        this.py = py;
        return this;
    }

    public CameraInvert translate_3D(float x, float y, float z) {
        mCamera.translate(x, -y, -z);
        return this;
    }

    float px, py;


    public CameraInvert rotateY_3D(float degrees) {
        mCamera.rotateY(-degrees);
        return this;

    }


    public CameraInvert rotateX_3D(float degrees) {
        mCamera.rotateX(-degrees);
        return this;
    }


    public CameraInvert rotateZ_3D(float degrees) {
        mCamera.rotateZ(-degrees);
        return this;
    }

    public void getMatrix_fix3D_MPERSP(Matrix matrix, Context context) {

        mCamera.getMatrix(matrix);
        if (context != null) {

            // 修正失真，主要修改 MPERSP_0 和 MPERSP_1
            float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;

            float[] mValues = new float[9];
            matrix.getValues(mValues);              //获取数值
            mValues[6] = mValues[6] / scale;          //数值修正
            mValues[7] = mValues[7] / scale;          //数值修正
            matrix.setValues(mValues);
        }

        if (px != 0 || py != 0) {
            matrix.preTranslate(-px, -py);
            matrix.postTranslate(px, py);
        }
        //还原操作  不然每次的操作都叠加了
        resetZCamera();
    }

    public void postConcat(Matrix matrix) {
        postConcat_fix3D_MPERSP(matrix, null);
    }

    public void postConcat_fix3D_MPERSP(Matrix matrix, Context context) {
        Matrix tempMatrix = new Matrix();
        getMatrix_fix3D_MPERSP(tempMatrix, context);
        matrix.postConcat(tempMatrix);
    }

    public void reset() {
        resetZCamera();
        px = py = 0;
    }

    public void resetZCamera() {

    }


    public void getMatrix(Matrix matrix) {
        getMatrix_fix3D_MPERSP(matrix, null);

    }


    public void preConcat(Matrix matrix) {
        preConcat_fix3D_MPERSP(matrix, null);
    }


    public void preConcat_fix3D_MPERSP(Matrix matrix, Context context) {
        Matrix tempMatrix = new Matrix();
        getMatrix_fix3D_MPERSP(tempMatrix, context);
        matrix.preConcat(tempMatrix);
    }


}
