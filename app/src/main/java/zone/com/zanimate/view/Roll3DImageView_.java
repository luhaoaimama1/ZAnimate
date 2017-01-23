package zone.com.zanimate.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

//参考：Roll3DImageView:https://github.com/zhangyuChen1991/Roll3DImageView
public class Roll3DImageView_ extends FrameLayout {

    private float rotateDegree;
    private int viewHeight;
    private double axisY;
    private int viewWidth;

    public Roll3DImageView_(Context context) {
        super(context);
    }

    public Roll3DImageView_(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Roll3DImageView_(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewHeight = h;
        this.viewWidth = w;
    }

    Camera camera = new Camera();
    Matrix matrix = new Matrix();

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
        View first = getChildAt(0);
        View second = getChildAt(1);
        matrix.reset();


        camera.save();
        camera.rotateX(-rotateDegree);
        camera.getMatrix(matrix);
        camera.restore();

        // -viewWidth / 2 保持着竖线高度不变的，其他会倾斜；
        matrix.preTranslate(-viewWidth / 2, 0);
        matrix.postTranslate(viewWidth / 2, (float) axisY);
        drawScreen(canvas, first, matrix);
        //
        matrix.reset();
        camera.save();
        camera.rotateX((90 - rotateDegree));
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-viewWidth / 2, -viewHeight);
        matrix.postTranslate(viewWidth / 2, (float) axisY);
        drawScreen(canvas, second, matrix);
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
