package zone.com.zanimate

import android.annotation.TargetApi
import android.graphics.Camera
import android.graphics.Matrix
import android.os.Build
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.nineoldandroids.animation.ValueAnimator
import butterknife.ButterKnife
import com.qyzc.medical.smartbodydevice.ui.adapter.delegate.core.ViewDelegatesDemo
import com.zone.adapter3kt.QuickAdapter
import com.zone.adapter3kt.data.DataWarp
import zone.com.zanimate.`object`.ObjectAnimatorHelper
import zone.com.zanimate.`object`.ObjectAnimatorProxy
import zone.com.zanimate.value.ValueAnimatorProxy
import zone.com.zanimate.view.PhotoView

import com.zone.lib.base.activity.BaseAppCompatActivity
import com.zone.lib.base.activity.kinds.SwipeBackKind
import kotlinx.android.synthetic.main.a_ani.*
import zone.com.zanimate.adapter.HolderExDemoImpl


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

class _AniActivity : BaseAppCompatActivity() {

    private val propertys = arrayListOf("tX", "tY", "tZ", "rX", "rY", "rZ")


    override fun setContentView() {
        setContentView(R.layout.a_ani)
        ButterKnife.bind(this)
    }

    override fun updateKinds() {
        super.updateKinds()
        mKindControl.remove(SwipeBackKind::class.java)
    }

    override fun findIDs() {}

    override fun initData() {
        view()
        camera()
    }

    private fun camera() {
        rv_Camera.layoutManager = GridLayoutManager(this, 3)
        rv_Camera.adapter = QuickAdapter<String>(this).apply {
            registerDelegate(object : ViewDelegatesDemo<String>() {
                override val layoutId: Int = R.layout.item_ani

                override fun onBindViewHolder(position: Int, item: DataWarp<String>, baseHolder: HolderExDemoImpl, payloads: List<*>) {

                    item.data?.apply{
                        baseHolder.setText(R.id.tv_property,this)

                        val cb = baseHolder.getView<CheckBox>(R.id.cb)
                        val tv_range = baseHolder.getView<TextView>(R.id.tv_range)
                        val iv = baseHolder.getView<PhotoView>(R.id.iv)
                        val ob = ValueAnimatorProxy.ofFloat(0F, 300F)
                                .setRepeatCount(ValueAnimator.INFINITE).setDuration(2500)
                        iv.post(Runnable { openAni(this, ob, cb, tv_range, iv) })
                        cb.setOnCheckedChangeListener({ buttonView, isChecked -> openAni(this, ob, cb, tv_range, iv) })
                    }

                }
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                private fun cameraAni(marix: Matrix, camera: Camera, x: Float, y: Float, z: Float, rx: Float, ry: Float, rz: Float, iv: View, item: String) {
                    marix.reset()
                    camera.save()
                    camera.translate(x, y, z)
                    camera.rotate(rx, ry, rz)
                    //                camera.rotateX(rx);
                    //                camera.rotateY(ry);
                    //                camera.rotateZ(rz);
                    camera.getMatrix(marix)
                    //                StringBuilder sb=new StringBuilder();
                    //                sb.append("{LocationX:"+camera.getLocationX()+",");
                    //                sb.append("LocationY:"+camera.getLocationY()+",");
                    //                sb.append("LocationZ:"+camera.getLocationZ()+"___>");
                    camera.restore()
                    marix.preTranslate((-iv.width / 2).toFloat(), (-iv.height / 2).toFloat())
                    marix.postTranslate((iv.width / 2).toFloat(), (iv.height / 2).toFloat())
                    //                sb.append(marix.toString()+"}");
                    //                Log.i("gan:_"+item,sb.toString());
                }

                private fun openAni(item: String, valueAnimator: ValueAnimatorProxy, cb: CheckBox,
                                    tv_range: TextView, iv: PhotoView) {
                    if (valueAnimator.isRunning)
                        valueAnimator.end()
                    when (item) {
                        "tX" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * iv.width / 2 + "]"
                            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                                var camera = Camera()
                                var matrix = Matrix()

                                override fun onAnimationUpdate(animation: ValueAnimator) {
                                    cameraAni(matrix, camera, animation.animatedValue as Float, 0f, 0f, 0f, 0f, 0f, iv, item)
                                    iv.setImageMatrix(matrix)

                                }

                            }).setFloatValues(0F, getAnInt(cb) * iv.width.toFloat() / 2).start()
                        }
                        "tY" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * iv.height / 2 + "]"
                            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                                var camera = Camera()
                                var matrix = Matrix()

                                override fun onAnimationUpdate(animation: ValueAnimator) {
                                    cameraAni(matrix, camera, 0f, animation.animatedValue as Float, 0f, 0f, 0f, 0f, iv, item)
                                    iv.setImageMatrix(matrix)

                                }

                            }).setFloatValues(0F, getAnInt(cb) * iv.width.toFloat() / 2).start()
                        }
                        "tZ" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * iv.height / 2 + "]"
                            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                                var camera = Camera()
                                var matrix = Matrix()

                                override fun onAnimationUpdate(animation: ValueAnimator) {
                                    cameraAni(matrix, camera, 0f, 0f, animation.animatedValue as Float, 0f, 0f, 0f, iv, item)
                                    iv.setImageMatrix(matrix)

                                }

                            }).setFloatValues(0F, getAnInt(cb) * iv.width.toFloat() / 2).start()
                        }
                        "rX" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * 360 + "]"
                            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                                var camera = Camera()
                                var matrix = Matrix()

                                override fun onAnimationUpdate(animation: ValueAnimator) {
                                    cameraAni(matrix, camera, 0f, 0f, 0f, animation.animatedValue as Float, 0f, 0f, iv, item)
                                    iv.setImageMatrix(matrix)

                                }

                            }).setFloatValues(0F, getAnInt(cb) * 360F).start()
                        }
                        "rY" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * 360 + "]"
                            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                                var camera = Camera()
                                var matrix = Matrix()

                                override fun onAnimationUpdate(animation: ValueAnimator) {
                                    cameraAni(matrix, camera, 0f, 0f, 0f, 0f, animation.animatedValue as Float, 0f, iv, item)
                                    iv.setImageMatrix(matrix)

                                }

                            }).setFloatValues(0F, getAnInt(cb) * 360F).start()
                        }
                        "rZ" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * 360 + "]"
                            valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                                var camera = Camera()
                                var matrix = Matrix()

                                override fun onAnimationUpdate(animation: ValueAnimator) {
                                    cameraAni(matrix, camera, 0f, 0f, 0f, 0f, 0f, animation.animatedValue as Float, iv, item)
                                    iv.setImageMatrix(matrix)

                                }

                            }).setFloatValues(0F, getAnInt(cb) * 360F).start()
                        }
                    }

                }

                private fun getAnInt(cb: CheckBox): Int {
                    return if (cb.isChecked) -1 else 1
                }

            })
            add(propertys)
        }

    }

    private fun view() {
        rv_view.layoutManager = GridLayoutManager(this, 3)
        rv_view.adapter = QuickAdapter<String>(this).apply {

            registerDelegate(object : ViewDelegatesDemo<String>() {
                override val layoutId: Int =R.layout.item_ani

                override fun onBindViewHolder(position: Int, item: DataWarp<String>, baseHolder: HolderExDemoImpl, payloads: List<*>) {
                    item.data?.apply {
                        baseHolder.setText(R.id.tv_property, this)

                        val cb = baseHolder.getView<CheckBox>(R.id.cb)
                        val tv_range = baseHolder.getView<TextView>(R.id.tv_range)
                        val iv = baseHolder.getView<PhotoView>(R.id.iv)
                        val ob = ObjectAnimatorHelper.ofFloatProxy(iv, "translationX", 0F, 300F)
                                .setRepeatCount(ValueAnimator.INFINITE).setDuration(2500)
                        openAni(this, ob, cb, tv_range, iv)
                        cb.setOnCheckedChangeListener({ buttonView, isChecked -> openAni(this, ob, cb, tv_range, iv) })

                    }

                }
                private fun openAni(item: String, valueAnimator: ObjectAnimatorProxy, cb: CheckBox,
                                    tv_range: TextView, iv: View) {
                    if (valueAnimator.isRunning)
                        valueAnimator.end()
                    when (item) {
                        "tX" -> iv.post {
                            tv_range.text = "value:[0," + getAnInt(cb) * iv.width / 2 + "]"
                            valueAnimator.setTarget(iv).setPropertyName("translationX").setFloatValues(0F, getAnInt(cb) * iv.width.toFloat() / 2).start()
                        }
                        "tY" -> iv.post {
                            tv_range.text = "value:[0," + getAnInt(cb) * iv.height / 2 + "]"
                            valueAnimator.setTarget(iv).setPropertyName("translationY").setFloatValues(0F, getAnInt(cb) * iv.width.toFloat() / 2).start()
                        }
                        "tZ" -> {
                        }
                        "rX" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * 360 + "]"
                            valueAnimator.setPropertyName("rotationX").setFloatValues(0F, getAnInt(cb) * 360F).start()
                        }
                        "rY" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * 360 + "]"
                            valueAnimator.setPropertyName("rotationY").setFloatValues(0F, getAnInt(cb) * 360F).start()
                        }
                        "rZ" -> {
                            tv_range.text = "value:[0," + getAnInt(cb) * 360 + "]"
                            valueAnimator.setPropertyName("rotation").setFloatValues(0F, getAnInt(cb) * 360F).start()
                        }
                    }

                }

                private fun getAnInt(cb: CheckBox): Int {
                    return if (cb.isChecked) -1 else 1
                }

            })
            add(propertys)
        }

    }

    override fun setListener() {

    }

}
