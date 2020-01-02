package zone.com.zanimate._3d

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View

import com.zone.lib.utils.image.compress2sample.SampleUtils
import com.zone.lib.utils.view.DrawUtils

import zone.com.zanimate.R

/**
 * Created by Administrator on 2016/9/16.
 */
class MatrixStudy @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


    private val matrix_: Matrix
    private val bm: Bitmap
    private val values: FloatArray

    init {
        bm = SampleUtils.load(context, R.drawable.abcd).bitmap()
        values = FloatArray(9)
        matrix_ = Matrix()
        matrix_.getValues(values)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bm, matrix_, DrawUtils.getBtPaint())

    }

    private fun refresh() {
        matrix_.setValues(values)
        invalidate()
    }

    operator fun set(postion: Int, change: Float) {
        values[postion] = change
        refresh()
    }

}
