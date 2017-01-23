package zone.com.zanimate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import zone.com.zanimate.camera.CameraCorrect;
import zone.com.zanimate.camera.Layer;

//参考：Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
public class Roll3DImageView_Lib extends FrameLayout {

    private float rotateDegree;
    private int viewHeight;
    private double axisY;
    private int viewWidth;
    private Layer layer1,layer2;

    public Roll3DImageView_Lib(Context context) {
        super(context);
    }

    public Roll3DImageView_Lib(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Roll3DImageView_Lib(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    CameraCorrect mCameraCorrect;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewHeight = h;
        this.viewWidth = w;
        mCameraCorrect = new CameraCorrect(viewWidth, viewHeight);
        layer1=new Layer();
        layer2=new Layer();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
        View first = getChildAt(0);
        View second = getChildAt(1);

        Matrix matrix = new Matrix();
        layer1.rotate(-rotateDegree, 0, 0)
                .getMatrix(matrix, mCameraCorrect.setPivot(CameraCorrect.PivotType.CenterTop));
        matrix.postTranslate(viewWidth/2, (float) axisY);

        drawScreen(canvas, first, matrix);


        Matrix matrix2 = new Matrix();
        layer2.rotate(90 - rotateDegree, 0, 0)
                .getMatrix(matrix2, mCameraCorrect.setPivot(CameraCorrect.PivotType.CenterBottom));
        matrix2.postTranslate(viewWidth/2, (float) axisY);

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
