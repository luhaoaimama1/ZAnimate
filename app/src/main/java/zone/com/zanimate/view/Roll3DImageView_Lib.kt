package zone.com.zanimate.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import zone.com.zanimate.camera.CameraCorrect
import zone.com.zanimate.camera.Layer

//参考：Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
class Roll3DImageView_Lib : FrameLayout {

    private var rotateDegree: Float = 0.toFloat()
    private var viewHeight: Int = 0
    private var axisY: Double = 0.toDouble()
    private var viewWidth: Int = 0
    private var layer1: Layer? = null
    private var layer2: Layer? = null

    var mCameraCorrect: CameraCorrect? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.viewHeight = h
        this.viewWidth = w
        mCameraCorrect = CameraCorrect(viewWidth, viewHeight)
        layer1 = Layer()
        layer2 = Layer()
    }


    override fun dispatchDraw(canvas: Canvas) {
        //        super.dispatchDraw(canvas);
        val first = getChildAt(0)
        val second = getChildAt(1)

        val matrix = Matrix()
        layer1!!.rotate(-rotateDegree, 0f, 0f)
                .getMatrix(matrix, mCameraCorrect?.setPivot(CameraCorrect.PivotType.CenterTop))
        matrix.postTranslate((viewWidth / 2).toFloat(), axisY.toFloat())

        drawScreen(canvas, first, matrix)


        val matrix2 = Matrix()
        layer2!!.rotate(90 - rotateDegree, 0f, 0f)
                .getMatrix(matrix2, mCameraCorrect?.setPivot(CameraCorrect.PivotType.CenterBottom))
        matrix2.postTranslate((viewWidth / 2).toFloat(), axisY.toFloat())

        drawScreen(canvas, second, matrix2)
    }

    private fun drawScreen(canvas: Canvas, v: View, matrix: Matrix) {
        canvas.save()
        canvas.concat(matrix)
        drawChild(canvas, v, drawingTime)
        canvas.restore()
    }

    fun setRotateDegree(rotateDegree: Float) {//mark
        this.rotateDegree = rotateDegree
        axisY = Math.sin(Math.toRadians(rotateDegree.toDouble())) * viewHeight
        invalidate()
    }
}
