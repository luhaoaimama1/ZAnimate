package zone.com.zanimate._3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import and.utils.image.compress2sample.SampleUtils;
import zone.com.zanimate.R;

/**
 * Created by fuzhipeng on 16/10/4.
 */

public class View_3D extends View {
    public final Bitmap bt;
    private Matrix matrix;

    public View_3D(Context context) {
        this(context, null);
    }

    public View_3D(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_3D(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bt = SampleUtils.load(context, R.drawable.abcd).override(200,200).bitmap();
    }

    public void invalidate(Matrix matrix) {
        this.matrix = matrix;
        System.out.println("matrix:"+matrix.toString());
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate((getWidth() - bt.getWidth()) / 2, (getHeight() - bt.getHeight()) / 2);
        if (matrix != null)
            canvas.drawBitmap(bt, matrix, null);
        else
            canvas.drawBitmap(bt, 0, 0, null);
        canvas.restore();
    }
}
