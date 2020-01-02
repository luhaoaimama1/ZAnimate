package zone.com.zanimate.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

import com.zone.lib.utils.image.BitmapUtils
import com.zone.lib.utils.image.compress2sample.SampleUtils
import com.zone.lib.utils.view.DrawUtils

import zone.com.zanimate.R

/**
 * MIT License
 * Copyright (c) [2017] [Zone]
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

class PhotoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var bt: Bitmap? = null
    private var matrix_: Matrix? = null
    private var matrixStart: Matrix? = null
    private var matrixEnd: Matrix? = null
    internal var paint = DrawUtils.getStrokePaint(Paint.Style.FILL, 5f)
    internal var paintS = DrawUtils.getStrokePaint(Paint.Style.FILL, 7f)
    internal var paintE = DrawUtils.getStrokePaint(Paint.Style.FILL, 7f)
    internal var src = floatArrayOf(0f, 0f)
    internal var dst = floatArrayOf(0f, 0f)
    internal var dstEnd = floatArrayOf(0f, 0f)

    init {
        init()
        paint.color = Color.RED
        paintS.color = Color.GREEN
        paintE.color = Color.BLUE
    }

    private fun init() {
        bt = SampleUtils.load(context, R.drawable.wave).bitmap()
        bt = BitmapUtils.scaleBitmap(bt, 0.5f, 0.5f, false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(bt!!.width, bt!!.height)
    }

    fun setImageMatrix(matrix: Matrix) {
        this.matrix_ = matrix
        invalidate()
    }

    fun setLineMatrix(matrixStart: Matrix, matrixEnd: Matrix) {
        this.matrixStart = matrixStart
        this.matrixEnd = matrixEnd
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (matrix_ != null)
            canvas.drawBitmap(bt!!, matrix_!!, null)
        else
            canvas.drawBitmap(bt!!, 0f, 0f, null)

        if (matrixStart != null && matrixEnd != null) {
            matrixStart!!.mapPoints(dst, src)
            matrixEnd!!.mapPoints(dstEnd, src)
            canvas.drawLine(dst[0], dst[1], dstEnd[0], dstEnd[1], paint)
            canvas.drawPoint(dst[0], dst[1], paintS)
            canvas.drawPoint(dstEnd[0], dstEnd[1], paintE)
        }

    }
}
