package zone.com.zanimate.view

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

//参考：Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
class Roll3DImageView_ : FrameLayout {

    private var rotateDegree: Float = 0.toFloat()
    private var viewHeight: Int = 0
    private var axisY: Double = 0.toDouble()
    private var viewWidth: Int = 0

    internal var camera = Camera()
    internal var matrix = Matrix()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.viewHeight = h
        this.viewWidth = w
    }

    override fun dispatchDraw(canvas: Canvas) {
        //        super.dispatchDraw(canvas);
        val first = getChildAt(0)
        val second = getChildAt(1)
        matrix.reset()


        camera.save()
        camera.rotateX(-rotateDegree)
        camera.getMatrix(matrix)
        camera.restore()

        // -viewWidth / 2 保持着竖线高度不变的，其他会倾斜；
        matrix.preTranslate((-viewWidth / 2).toFloat(), 0f)
        matrix.postTranslate((viewWidth / 2).toFloat(), axisY.toFloat())
        drawScreen(canvas, first, matrix)
        //
        matrix.reset()
        camera.save()
        camera.rotateX(90 - rotateDegree)
        camera.getMatrix(matrix)
        camera.restore()

        matrix.preTranslate((-viewWidth / 2).toFloat(), (-viewHeight).toFloat())
        matrix.postTranslate((viewWidth / 2).toFloat(), axisY.toFloat())
        drawScreen(canvas, second, matrix)
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
