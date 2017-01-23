package zone.com.zanimate.camera;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by fuzhipeng on 16/9/15.
 * <p>
 * 更正  Camera 坐标系; 为左手坐标系  和view 动画相同；同时左手坐标系 并且完全准守;
 * <p>
 * 图层在旋转与位移 原因:因为location 没有因为旋转和位移更改位置;
 */
public class CameraCorrect extends Camera {
    private final int layerWidth, layerHeight;
    private float px, py;
    private PivotType pivotType = PivotType.None;

    public CameraCorrect(int layerWidth, int layerHeight) {
        this.layerWidth = layerWidth;
        this.layerHeight = layerHeight;
    }
    public CameraCorrect(View view) {
        this.layerWidth = view.getWidth();
        this.layerHeight = view.getHeight();
    }
    public CameraCorrect(Bitmap bt) {
        this.layerWidth = bt.getWidth();
        this.layerHeight = bt.getHeight();
    }

    public CameraCorrect(Rect rect) {
        this.layerWidth = rect.width();
        this.layerHeight = rect.height();
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


    /**
     *  仅仅更改锚点,而不移动合成
     * @param px
     * @param py
     * @return
     */
    public CameraCorrect setPivot(float px, float py) {
        this.pivotType = PivotType.None;
        this.px = px;
        this.py = py;
        return this;
    }

    /**
     * 仅仅更改锚点,而不移动合成
     * @param pivotType
     * @return
     */
    public CameraCorrect setPivot(PivotType pivotType) {
        this.pivotType = pivotType;
        return this;
    }

    /**
     * 仅仅更改锚点,而不移动合成
     * 既仅仅preTranslate,没有postTranslate.
     * @param matrix
     */
    @Override
    public void getMatrix(Matrix matrix) {
        super.getMatrix(matrix);

        switch (pivotType) {
            case None:
                matrix.preTranslate(-px, -py);
                break;
            case LeftTop:
                matrix.preTranslate(0, 0);
                break;
            case LeftCenter:
                matrix.preTranslate(0, -layerHeight / 2);
                break;
            case LeftBottom:
                matrix.preTranslate(0, -layerHeight);
                break;

            case RightTop:
                matrix.preTranslate(-layerWidth, 0);
                break;
            case RightCenter:
                matrix.preTranslate(-layerWidth, -layerHeight / 2);
                break;
            case RightBottom:
                matrix.preTranslate(-layerWidth, -layerHeight);
                break;

            case CenterTop:
                matrix.preTranslate(-layerWidth / 2, 0);
                break;
            case Center:
                matrix.preTranslate(-layerWidth / 2, -layerHeight / 2);
                break;
            case CenterBottom:
                matrix.preTranslate(-layerWidth / 2, -layerHeight);
                break;
        }

    }

    /**
     * 0---1---2
     * |       |
     * 7   8   3
     * |       |
     * 6---5---4
     */
    public enum PivotType {
        None,
        LeftTop, LeftCenter, LeftBottom,
        RightTop, RightCenter, RightBottom,
        CenterTop, Center, CenterBottom,
    }

}
