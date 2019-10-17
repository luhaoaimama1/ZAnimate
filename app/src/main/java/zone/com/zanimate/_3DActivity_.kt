package zone.com.zanimate

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.zone.adapter3kt.QuickAdapter
import android.widget.LinearLayout
import butterknife.ButterKnife
import butterknife.OnClick
import com.qyzc.medical.smartbodydevice.ui.adapter.delegate.core.ViewDelegatesDemo
import com.zone.adapter3kt.data.DataWarp
import com.zone.lib.base.activity.BaseActivity
import com.zone.lib.base.activity.kinds.SwipeBackKind
import kotlinx.android.synthetic.main.a_3d_.*
import zone.com.zanimate.adapter.HolderExDemoImpl
import zone.com.zanimate.camera.Layer
import zone.com.zanimate.view.TouchProgressView

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

class _3DActivity_ : BaseActivity() {
    private var first: Layer? = null
    private var second: Layer? = null
    private var third: Layer? = null
    var now: Layer? = null
    internal var buttons = arrayOfNulls<Button>(3)
    private val propertys = arrayListOf("tX", "tY", "tZ", "rX", "rY", "rZ")
    private var adapter: QuickAdapter<String>? = null

    override fun updateKinds() {
        super.updateKinds()
        mKindControl.remove(SwipeBackKind::class.java)
    }

    override fun setContentView() {
        setContentView(R.layout.a_3d_)
        ButterKnife.bind(this)

    }

    override fun findIDs() {

    }

    override fun initData() {
        first = Layer()
        second = Layer()
        third = Layer()
        buttons[0] = bt_first
        buttons[1] = bt_Second
        buttons[2] = bt_Third
        view_3D!!.setZLayerFinal(first, second, third!!)
        gridAdapter()
        bt_first!!.performClick()

    }

    override fun setListener() {

    }

    @OnClick(R.id.bt_first, R.id.bt_Second, R.id.bt_Third)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.bt_first -> {
                click(bt_first)
                now = first
            }
            R.id.bt_Second -> {
                click(bt_Second)
                now = second
            }
            R.id.bt_Third -> {
                click(bt_Third)
                now = third
            }
        }
        adapter!!.notifyDataSetChanged()
    }

    private fun click(bt_first: Button?) {
        for (button in buttons)
            button?.setBackgroundColor(Color.WHITE)
        bt_first!!.setBackgroundColor(Color.YELLOW)
    }


    private fun gridAdapter() {
        rv.layoutManager = GridLayoutManager(this, 3)

        adapter = QuickAdapter<String>(this).apply {
            registerDelegate(object : ViewDelegatesDemo<String>() {
                override val layoutId: Int = R.layout.item_cameralib

                override fun onBindViewHolder(position: Int, item: DataWarp<String>, baseHolder: HolderExDemoImpl, payloads: List<*>) {
                    val tv = baseHolder.getView<TextView>(R.id.tv)
                    val value = baseHolder.getView<TextView>(R.id.value)
                    val ll_main = baseHolder.getView<LinearLayout>(R.id.ll_main)
                    val tpv = baseHolder.getView<TouchProgressView>(R.id.tpv)
                    tv.text = "${item.data}:"

                    tpv.rang = object : TouchProgressView.Rang {
                        var start: Int = 0

                        override fun start() {
                            start = Integer.parseInt(value.getText().toString())
                        }

                        override fun rang(deltaX: Int) {
                            val temp = start + deltaX
                            value.text = temp.toString()
                            now?.apply {
                                setValue2(temp, this)
                            }
                            view_3D?.setZLayerFinal(first, second, third)

                        }

                        private fun setValue2(temp: Int, now2: Layer) {
                            when (item.data) {
                                "tX" -> now2.translateX(temp.toFloat())
                                "tY" -> now2.translateY(temp.toFloat())
                                "tZ" -> now2.translateZ(temp.toFloat())
                                "rX" -> now2.rorateX(temp.toFloat())
                                "rY" -> now2.rorateY(temp.toFloat())
                                "rZ" -> now2.rorateZ(temp.toFloat())
                            }
                        }
                    }
                    ll_main.setOnClickListener {
                        value.text = "0"
                        tpv.rang.start()
                        tpv.rang.rang(0)
                    }
                    now?.apply {
                        setValue(item, value, this)
                    }
                }

                private fun _3DActivity_.setValue(item: DataWarp<String>, value: TextView, now2: Layer) {
                    when (item.data) {
                        "tX" -> value.setText(now2.translateX.toInt())
                        "tY" -> value.setText(now2.translateY.toInt())
                        "tZ" -> value.setText(now2.translateZ.toInt())
                        "rX" -> value.setText(now2.rorateX.toInt())
                        "rY" -> value.setText(now2.rorateY.toInt())
                        "rZ" -> value.setText(now2.rorateZ.toInt())
                    }
                }
            })
            add(propertys)
        }
        rv.adapter = adapter
    }


}
