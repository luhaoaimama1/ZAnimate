package zone.com.zanimate.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

import com.zone.lib.utils.image.BitmapUtils
import com.zone.lib.utils.image.compress2sample.SampleUtils
import com.zone.lib.utils.view.DrawUtils

import zone.com.zanimate.R
import zone.com.zanimate.camera.CameraCorrect
import zone.com.zanimate.camera.Layer

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

class View_3D @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var bt: Bitmap? = null
    private val matrix: Matrix
    private val mCameraCorrect: CameraCorrect
    private var first: Layer? = null
    private var second: Layer? = null
    private var third: Layer? = null
    internal var paintRed = DrawUtils.getStrokePaint(Paint.Style.FILL, 5f)
    internal var paintGreen = DrawUtils.getStrokePaint(Paint.Style.FILL, 7f)
    internal var paintBlue = DrawUtils.getStrokePaint(Paint.Style.FILL, 7f)

    init {
        bt = SampleUtils.load(getContext(), R.drawable.wave).bitmap()
        bt = BitmapUtils.scaleBitmap(bt, 0.5f, 0.5f, false)
        matrix = Matrix()
        mCameraCorrect = CameraCorrect(bt!!.width, bt!!.height)
        mCameraCorrect.translate(0f, 0f, -500f)
        paintRed.color = Color.RED
        paintGreen.color = Color.GREEN
        paintBlue.color = Color.BLUE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (third == null)
            return


        var rect = Rect(0, 0, 140, 140)
        third!!.getMatrix(matrix, mCameraCorrect.setPivot(CameraCorrect.PivotType.LeftTop))
        matrix.postTranslate((width / 2).toFloat(), 200f)
        drawRect(canvas, rect, paintBlue)

        rect = Rect(0, 0, 120, 120)
        second!!.getMatrix(matrix, mCameraCorrect.setPivot(CameraCorrect.PivotType.LeftTop))
        matrix.postTranslate((width / 2).toFloat(), 200f)
        drawRect(canvas, rect, paintGreen)

        rect = Rect(0, 0, 100, 100)
        first!!.getMatrix(matrix, mCameraCorrect.setPivot(0f, 0f))
        matrix.postTranslate((width / 2).toFloat(), 200f)
        drawRect(canvas, rect, paintRed)
    }

    private fun drawRect(canvas: Canvas, rect: Rect, paint: Paint) {
        canvas.save()
        canvas.concat(matrix)
        canvas.drawRect(rect, paint)
        canvas.restore()
    }

    fun setZLayerFinal(first: Layer, second: Layer, third: Layer) {
        this.first = first
        this.second = second
        this.third = third
        third.bindParent(second)
        second.bindParent(first)
        invalidate()
    }
}
