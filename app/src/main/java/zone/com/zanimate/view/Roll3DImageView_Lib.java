package zone.com.zanimate.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import zone.com.zanimate.camera.Layer;
import zone.com.zanimate.camera.LayerParent;
import zone.com.zanimate.camera.ZCameraFinal;
import zone.com.zanimate.camera.ZLayerFinal;

//参考：Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
public class Roll3DImageView_Lib extends FrameLayout {

    private float rotateDegree;
    private int viewHeight;
    private double axisY;
    private int viewWidth;

    public Roll3DImageView_Lib(Context context) {
        super(context);
    }

    public Roll3DImageView_Lib(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Roll3DImageView_Lib(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    ZCameraFinal mZCameraFinal;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewHeight = h;
        this.viewWidth = w;
        mZCameraFinal=new ZCameraFinal(viewWidth,viewHeight);
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
        View first = getChildAt(0);
        View second = getChildAt(1);

//        Matrix matrix=new Matrix();
//        new ZLayerFinal()
//                .translate(0,0,-800)
//                .rotate(-rotateDegree,0,0)
//        .getMatrix(matrix,mZCameraFinal.setPivot(viewWidth / 2,0));
//        matrix.postTranslate(0,(float) axisY);
//
//        drawScreen(canvas, first, matrix);
//
//
//        Matrix matrix2=new Matrix();
//        new ZLayerFinal()
//                .translate(0,0,-800)
//                .rotate(90-rotateDegree,0,0)
//        .getMatrix(matrix2,mZCameraFinal.setPivot(viewWidth / 2,viewHeight));
//        matrix2.postTranslate(0,-viewHeight+(float) axisY);
//
//        drawScreen(canvas, second, matrix2);

        Matrix matrix=new Matrix();
        new ZLayerFinal()
//                .translate(0,(float)axisY,-800)
//                .translate(0,(float)axisY,0)
                .rotate(-rotateDegree,0,0)
        .getMatrix(matrix,mZCameraFinal.setPivot(viewWidth / 2,0));
        matrix.postTranslate(0,(float) axisY);

        drawScreen(canvas, first, matrix);


        Matrix matrix2=new Matrix();
        new ZLayerFinal()
//                .translate(0,-viewHeight+(float) axisY,-800)
//                .translate(0,-viewHeight+(float) axisY,0)
                .rotate(90-rotateDegree,0,0)
        .getMatrix(matrix2,mZCameraFinal.setPivot(viewWidth / 2,viewHeight));
        matrix2.postTranslate(0,-viewHeight+(float) axisY);

        drawScreen(canvas, second, matrix2);
    }

    private void drawScreen(Canvas canvas, View v, Matrix matrix) {
        canvas.save();
        canvas.concat(matrix);
        drawChild(canvas, v, getDrawingTime());
        canvas.restore();
    }

    public void setRotateDegree(float rotateDegree) {//mark
        this.rotateDegree = rotateDegree;
        axisY = Math.sin(Math.toRadians(rotateDegree)) * viewHeight;
        invalidate();
    }
}
