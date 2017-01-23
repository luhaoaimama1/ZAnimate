package zone.com.zanimate;

import android.graphics.Matrix;
import android.view.View;
import android.widget.TextView;

import com.zone.adapter.QuickAdapter;
import com.zone.adapter.callback.Helper;
import com.zone.view.list.NoScrollGridView;

import java.util.List;

import and.base.activity.BaseActivity;
import and.base.activity.kinds.SwipeBackKind;
import and.utils.data.convert.ArraysUtil;
import and.utils.view.ViewUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import zone.com.zanimate.camera.ZCameraFinal;
import zone.com.zanimate.view.PhotoView;
import zone.com.zanimate.view.TouchProgressView;

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

public class _AniActivity2 extends BaseActivity {
    @Bind(R.id.gv_CameraLib)
    NoScrollGridView gvCameraLib;
    @Bind(R.id.iv)
    PhotoView iv;


    private String[] cameraPropertys = new String[]{
            "aX", "aY", "aZ",
            "tX", "tY", "tZ",
            "rX", "rY", "rZ",
    };
    private int[] cameraPropertysValues = new int[9];
    private List<String> cameraPropertysList;


    @Override
    public void setContentView() {
        setContentView(R.layout.a_ani2);
        ButterKnife.bind(this);
    }

    @Override
    public void updateKinds() {
        super.updateKinds();
        mKindControl.remove(SwipeBackKind.class);
    }

    @Override
    public void findIDs() {
        iv.post(new Runnable() {
            @Override
            public void run() {
                ViewUtils.recurrenceClipChildren(iv, false);
            }
        });
    }

    @Override
    public void initData() {
        cameraLib();
    }

    private void cameraLib() {
        gvCameraLib.setAdapter(new QuickAdapter<String>(_AniActivity2.this, cameraPropertysList = ArraysUtil.asList(cameraPropertys)) {

            @Override
            public void fillData(Helper<String> helper, final String item, boolean itemChanged, int layoutId) {
                final TextView tv = helper.getView(R.id.tv);
                tv.setText(item + ":" + cameraPropertysValues[cameraPropertysList.indexOf(item)]);
                final TouchProgressView tpv = helper.getView(R.id.tpv);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cameraPropertysValues[cameraPropertysList.indexOf(item)] = 0;
                        tv.setText(item + ":" + cameraPropertysValues[cameraPropertysList.indexOf(item)]);
                        iv.post(new Runnable() {
                            @Override
                            public void run() {
                                matrix_();
                            }
                        });
                    }
                });
                tpv.setRang(new TouchProgressView.Rang() {
                    public int initPro;

                    @Override
                    public void start() {
                        initPro = getTvCount();
                    }

                    @Override
                    public void rang(int deltaX) {
                        cameraPropertysValues[cameraPropertysList.indexOf(item)] = initPro + deltaX;
                        tv.setText(item + ":" + cameraPropertysValues[cameraPropertysList.indexOf(item)]);
                        iv.post(new Runnable() {
                            @Override
                            public void run() {
                                matrix_();
                            }
                        });

                    }

                    private int getTvCount() {
                        int pro = Integer.parseInt(tv.getText().toString().split(":")[1]);
                        return pro;
                    }
                });

            }

            private void matrix_() {
                Matrix matrix = new Matrix();
                ZCameraFinal zCameraFinal = new ZCameraFinal(iv.getWidth(), iv.getHeight());
                zCameraFinal.save();

                zCameraFinal.translate(0, 0, cameraPropertysValues[2]);
//                zCameraFinal.translate(-cameraPropertysValues[0], -cameraPropertysValues[1], cameraPropertysValues[2]);
                zCameraFinal.rotate(cameraPropertysValues[6], cameraPropertysValues[7], cameraPropertysValues[8]);
                zCameraFinal.translate(cameraPropertysValues[3]
                        , cameraPropertysValues[4], cameraPropertysValues[5]);

                zCameraFinal.getMatrix(matrix);
                zCameraFinal.restore();
                matrix.preTranslate(-cameraPropertysValues[0], -cameraPropertysValues[1]);
                matrix.postTranslate(cameraPropertysValues[0], cameraPropertysValues[1]);
                iv.setImageMatrix(matrix);



                zCameraFinal.save();
                Matrix matri2x = new Matrix();
                zCameraFinal.translate(0,0,cameraPropertysValues[2]);
                zCameraFinal.translate(cameraPropertysValues[3]
                        , cameraPropertysValues[4], cameraPropertysValues[5]);
                zCameraFinal.getMatrix(matri2x);
                zCameraFinal.restore();
//                matri2x.preTranslate(cameraPropertysValues[0], cameraPropertysValues[1]);
                matri2x.postTranslate(cameraPropertysValues[0], cameraPropertysValues[1]);


                zCameraFinal.save();
                Matrix matriEnd = new Matrix();
                zCameraFinal.translate(0,0, cameraPropertysValues[2]);
                zCameraFinal.rotate(cameraPropertysValues[6], cameraPropertysValues[7], cameraPropertysValues[8]);
                zCameraFinal.translate(cameraPropertysValues[3]
                        , cameraPropertysValues[4], cameraPropertysValues[5]);

                zCameraFinal.getMatrix(matriEnd);
                zCameraFinal.restore();
//                matriEnd.preTranslate(cameraPropertysValues[0], cameraPropertysValues[1]);
                matriEnd.postTranslate(cameraPropertysValues[0], cameraPropertysValues[1]);

                iv.setLineMatrix(matri2x,matriEnd);

            }


            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.item_cameralib;
            }
        });
    }

    @Override
    public void setListener() {

    }

}
