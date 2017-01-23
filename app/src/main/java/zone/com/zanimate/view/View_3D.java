package zone.com.zanimate.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import and.utils.image.BitmapUtils;
import and.utils.image.compress2sample.SampleUtils;
import and.utils.view.graphics.DrawUtils;
import zone.com.zanimate.R;
import zone.com.zanimate.camera.CameraCorrect;
import zone.com.zanimate.camera.ZLayer;

/**
 * MIT License
 * Copyright (c) [2017] [Zone]
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class View_3D extends View {
    private Bitmap bt;
    private Matrix matrix;
    private CameraCorrect mCameraCorrect;
    private ZLayer first, second, third;
    Paint paintRed = DrawUtils.getStrokePaint(Paint.Style.FILL, 5);
    Paint paintGreen = DrawUtils.getStrokePaint(Paint.Style.FILL, 7);
    Paint paintBlue = DrawUtils.getStrokePaint(Paint.Style.FILL, 7);

    public View_3D(Context context) {
        this(context, null);
    }

    public View_3D(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_3D(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bt = SampleUtils.load(getContext(), R.drawable.wave).bitmap();
        bt = BitmapUtils.scaleBitmap(bt, 0.5F, 0.5F, false);
        matrix = new Matrix();
        mCameraCorrect = new CameraCorrect(bt.getWidth(), bt.getHeight());
        mCameraCorrect.translate(0, 0, -500);
        paintRed.setColor(Color.RED);
        paintGreen.setColor(Color.GREEN);
        paintBlue.setColor(Color.BLUE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (third == null)
            return;


        Rect rect = new Rect(0, 0, 140, 140);
        third.getMatrix(matrix, mCameraCorrect.setPivot(0, 0));
        matrix.postTranslate(getWidth() / 2, 200);
        drawRect(canvas, rect, paintBlue);

        rect = new Rect(0, 0, 120, 120);
        second.getMatrix(matrix, mCameraCorrect.setPivot(0, 0));
        matrix.postTranslate(getWidth() / 2, 200);
        drawRect(canvas, rect, paintGreen);

        rect = new Rect(0, 0, 100, 100);
        first.getMatrix(matrix, mCameraCorrect.setPivot(0, 0));
        matrix.postTranslate(getWidth() / 2, 200);
        drawRect(canvas, rect, paintRed);
    }

    private void drawRect(Canvas canvas, Rect rect, Paint paint) {
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rect, paint);
        canvas.restore();
    }

    public void setZLayerFinal(ZLayer first, ZLayer second, ZLayer third) {
        this.first = first;
        this.second = second;
        this.third = third;
        third.bindParent(second);
        second.bindParent(first);
        invalidate();
    }
}
