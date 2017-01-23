package zone.com.zanimate.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

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

public class TouchProgressView extends LinearLayout {
    private int downx;
    private int deltaX;

    public TouchProgressView(Context context) {
        this(context, null);
    }

    public TouchProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i("MotionEvent_dis",ev.toString());
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                rang.start();
//                downx = (int) ev.getX();
//                Log.i("rang_ACTION_DOWN",deltaX+"");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                deltaX = (int) ev.getX() - downx;
//                rang.rang(deltaX);
//                Log.i("rang_ACTION_MOVE",deltaX+"");
//            case MotionEvent.ACTION_UP:
//                deltaX = 0;
//                Log.i("rang_ACTION_UP",deltaX+"");
//                break;
//            default:
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("MotionEvent",ev.toString());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rang.start();
                downx = (int) ev.getX();
                Log.i("rang_ACTION_DOWN",deltaX+"");
                break;
            case MotionEvent.ACTION_MOVE:
                deltaX = (int) ev.getX() - downx;
                rang.rang(deltaX);
                Log.i("rang_ACTION_MOVE",deltaX+"");
            case MotionEvent.ACTION_UP:
                deltaX = 0;
                Log.i("rang_ACTION_UP",deltaX+"");
                break;
            default:
                break;
        }
        return true;
    }

    private Rang rang;

    public Rang getRang() {
        return rang;
    }

    public void setRang(Rang rang) {
        this.rang = rang;
    }

    public interface Rang {
        void start();
        void rang(int deltaX);
    }
}
