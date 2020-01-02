package zone.com.zanimate.camera

import android.graphics.Bitmap
import android.graphics.Camera
import android.graphics.Matrix
import android.graphics.Rect
import android.view.View

/**
 * Created by fuzhipeng on 16/9/15.
 *
 *
 * 更正  Camera 坐标系; 为左手坐标系  和view 动画相同；同时左手坐标系 并且完全准守;
 *
 *
 * 图层在旋转与位移 原因:因为location 没有因为旋转和位移更改位置;
 */
class CameraCorrect : Camera {
    private val layerWidth: Int
    private val layerHeight: Int
    private var px: Float = 0.toFloat()
    private var py: Float = 0.toFloat()
    private var pivotType = PivotType.None

    constructor(layerWidth: Int, layerHeight: Int) {
        this.layerWidth = layerWidth
        this.layerHeight = layerHeight
    }

    constructor(view: View) {
        this.layerWidth = view.width
        this.layerHeight = view.height
    }

    constructor(bt: Bitmap) {
        this.layerWidth = bt.width
        this.layerHeight = bt.height
    }

    constructor(rect: Rect) {
        this.layerWidth = rect.width()
        this.layerHeight = rect.height()
    }

    override fun translate(x: Float, y: Float, z: Float) {
        super.translate(x, -y, -z)
    }


    override fun rotate(x: Float, y: Float, z: Float) {
        super.rotate(x, y, -z)
    }


    override fun rotateZ(deg: Float) {
        super.rotateZ(-deg)
    }


    /**
     * 仅仅更改锚点,而不移动合成
     * @param px
     * @param py
     * @return
     */
    fun setPivot(px: Float, py: Float): CameraCorrect {
        this.pivotType = PivotType.None
        this.px = px
        this.py = py
        return this
    }

    /**
     * 仅仅更改锚点,而不移动合成
     * @param pivotType
     * @return
     */
    fun setPivot(pivotType: PivotType): CameraCorrect {
        this.pivotType = pivotType
        return this
    }

    /**
     * 仅仅更改锚点,而不移动合成
     * 既仅仅preTranslate,没有postTranslate.
     * @param matrix
     */
    override fun getMatrix(matrix: Matrix) {
        super.getMatrix(matrix)

        when (pivotType) {
            CameraCorrect.PivotType.None -> matrix.preTranslate(-px, -py)
            CameraCorrect.PivotType.LeftTop -> matrix.preTranslate(0f, 0f)
            CameraCorrect.PivotType.LeftCenter -> matrix.preTranslate(0f, (-layerHeight / 2).toFloat())
            CameraCorrect.PivotType.LeftBottom -> matrix.preTranslate(0f, (-layerHeight).toFloat())

            CameraCorrect.PivotType.RightTop -> matrix.preTranslate((-layerWidth).toFloat(), 0f)
            CameraCorrect.PivotType.RightCenter -> matrix.preTranslate((-layerWidth).toFloat(), (-layerHeight / 2).toFloat())
            CameraCorrect.PivotType.RightBottom -> matrix.preTranslate((-layerWidth).toFloat(), (-layerHeight).toFloat())

            CameraCorrect.PivotType.CenterTop -> matrix.preTranslate((-layerWidth / 2).toFloat(), 0f)
            CameraCorrect.PivotType.Center -> matrix.preTranslate((-layerWidth / 2).toFloat(), (-layerHeight / 2).toFloat())
            CameraCorrect.PivotType.CenterBottom -> matrix.preTranslate((-layerWidth / 2).toFloat(), (-layerHeight).toFloat())
        }

    }

    /**
     * 0---1---2
     * |       |
     * 7   8   3
     * |       |
     * 6---5---4
     */
    enum class PivotType {
        None,
        LeftTop, LeftCenter, LeftBottom,
        RightTop, RightCenter, RightBottom,
        CenterTop, Center, CenterBottom
    }

}
