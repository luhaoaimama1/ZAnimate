package zone.com.zanimate.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zone.lib.utils.image.BitmapUtils;
import com.zone.lib.utils.image.compress2sample.SampleUtils;
import com.zone.lib.utils.view.DrawUtils;

import zone.com.zanimate.R;

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

public class PhotoView extends View {
    private Bitmap bt;
    private Matrix matrix;
    private Matrix matrixStart;
    private Matrix matrixEnd;
    Paint paint= DrawUtils.getStrokePaint(Paint.Style.FILL,5);
    Paint paintS=DrawUtils.getStrokePaint(Paint.Style.FILL,7);
    Paint paintE=DrawUtils.getStrokePaint(Paint.Style.FILL,7);

    public PhotoView(Context context) {
        this(context,null);
    }

    public PhotoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        paint.setColor(Color.RED);
        paintS.setColor(Color.GREEN);
        paintE.setColor(Color.BLUE);
    }

    private void init() {
        bt= SampleUtils.load(getContext(), R.drawable.wave).bitmap();
        bt= BitmapUtils.scaleBitmap(bt,0.5F,0.5F,false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bt.getWidth(),bt.getHeight());
    }

    public void setImageMatrix(Matrix matrix){
        this.matrix=matrix;
        invalidate();
    }
    public void setLineMatrix(Matrix matrixStart,Matrix matrixEnd){
        this.matrixStart=matrixStart;
        this.matrixEnd=matrixEnd;
        invalidate();
    }
    float[] src = new float[]{0, 0};
    float[] dst = new float[]{0, 0};
    float[] dstEnd = new float[]{0, 0};
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(matrix!=null)
            canvas.drawBitmap(bt,matrix,null);
        else
            canvas.drawBitmap(bt,0,0,null);

        if(matrixStart!=null&&matrixEnd!=null){
            matrixStart.mapPoints(dst,src);
            matrixEnd.mapPoints(dstEnd,src);
            canvas.drawLine(dst[0],dst[1],dstEnd[0],dstEnd[1],paint);
            canvas.drawPoint(dst[0],dst[1],paintS);
            canvas.drawPoint(dstEnd[0],dstEnd[1],paintE);
        }

    }
}
