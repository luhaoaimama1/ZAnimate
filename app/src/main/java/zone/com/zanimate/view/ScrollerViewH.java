package zone.com.zanimate.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

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

public class ScrollerViewH extends ScrollView {
    private int downx, downy;

    public ScrollerViewH(Context context) {
        super(context);
    }

    public ScrollerViewH(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerViewH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercepter = true;
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                intercepter = false;
//                downx = (int) ev.getX();
//                downy = (int) ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = (int) ev.getX() - downx;
//                int deltaY = (int) ev.getY() - downy;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    intercepter = false;
//                } else {
//                    intercepter = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
//        if (intercepter)
            return super.onInterceptTouchEvent(ev);
//        else
//            return intercepter;

    }
}
