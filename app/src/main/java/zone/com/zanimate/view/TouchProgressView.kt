package zone.com.zanimate.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

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

class TouchProgressView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    private var downx: Int = 0
    private var deltaX: Int = 0

    var rang: Rang? = null

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

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        Log.i("MotionEvent", ev.toString())
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                rang!!.start()
                downx = ev.x.toInt()
                Log.i("rang_ACTION_DOWN", deltaX.toString() + "")
            }
            MotionEvent.ACTION_MOVE -> {
                deltaX = ev.x.toInt() - downx
                rang!!.rang(deltaX)
                Log.i("rang_ACTION_MOVE", deltaX.toString() + "")
                deltaX = 0
                Log.i("rang_ACTION_UP", deltaX.toString() + "")
            }
            MotionEvent.ACTION_UP -> {
                deltaX = 0
                Log.i("rang_ACTION_UP", deltaX.toString() + "")
            }
            else -> {
            }
        }
        return true
    }

    interface Rang {
        fun start()
        fun rang(deltaX: Int)
    }
}
