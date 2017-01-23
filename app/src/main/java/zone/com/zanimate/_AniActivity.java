package zone.com.zanimate;

import android.annotation.TargetApi;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.zone.adapter.QuickAdapter;
import com.zone.adapter.callback.Helper;
import com.zone.view.list.NoScrollGridView;

import and.base.activity.BaseActivity;
import and.base.activity.kinds.SwipeBackKind;
import and.utils.data.convert.ArraysUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import zone.com.zanimate.object.ObjectAnimatorHelper;
import zone.com.zanimate.object.ObjectAnimatorProxy;
import zone.com.zanimate.value.ValueAnimatorProxy;
import zone.com.zanimate.view.PhotoView;

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

public class _AniActivity extends BaseActivity {
    @Bind(R.id.gv_view)
    NoScrollGridView gvView;
    @Bind(R.id.gv_Camera)
    NoScrollGridView gvCamera;

    private String[] propertys = new String[]{
            "tX", "tY", "tZ",
            "rX", "rY", "rZ",
    };



    @Override
    public void setContentView() {
        setContentView(R.layout.a_ani);
        ButterKnife.bind(this);
    }

    @Override
    public void updateKinds() {
        super.updateKinds();
        mKindControl.remove(SwipeBackKind.class);
    }

    @Override
    public void findIDs() {
    }

    @Override
    public void initData() {
        view();
        camera();
    }

    private void camera() {
        gvCamera.setAdapter(new QuickAdapter<String>(_AniActivity.this, ArraysUtil.asList(propertys)) {

            @Override
            public void fillData(Helper<String> helper, final String item, boolean itemChanged, int layoutId) {


                helper.setText(R.id.tv_property, item);

                final CheckBox cb = helper.getView(R.id.cb);
                final TextView tv_range = helper.getView(R.id.tv_range);
                final PhotoView iv = helper.getView(R.id.iv);
                final ValueAnimatorProxy ob = ValueAnimatorProxy.ofFloat(0, 300)
                        .setRepeatCount(ValueAnimator.INFINITE).setDuration(2500);
                iv.post(new Runnable() {
                    @Override
                    public void run() {
                        openAni(item, ob, cb, tv_range, iv);
                    }
                });
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        openAni(item, ob, cb, tv_range, iv);
                    }
                });

            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            private void cameraAni(Matrix marix, Camera camera, float x, float y, float z, float rx, float ry, float rz, View iv, String item) {
                marix.reset();
                camera.save();
                camera.translate(x, y, z);
                camera.rotate(rx, ry, rz);
//                camera.rotateX(rx);
//                camera.rotateY(ry);
//                camera.rotateZ(rz);
                camera.getMatrix(marix);
//                StringBuilder sb=new StringBuilder();
//                sb.append("{LocationX:"+camera.getLocationX()+",");
//                sb.append("LocationY:"+camera.getLocationY()+",");
//                sb.append("LocationZ:"+camera.getLocationZ()+"___>");
                camera.restore();
                marix.preTranslate(-iv.getWidth() / 2, -iv.getHeight() / 2);
                marix.postTranslate(iv.getWidth() / 2, iv.getHeight() / 2);
//                sb.append(marix.toString()+"}");
//                Log.i("gan:_"+item,sb.toString());
            }

            private void openAni(final String item, final ValueAnimatorProxy valueAnimator, final CheckBox cb,
                                 final TextView tv_range, final PhotoView iv) {
                if (valueAnimator.isRunning())
                    valueAnimator.end();
                switch (item) {
                    case "tX":
                        tv_range.setText("value:[0," + getAnInt(cb) * iv.getWidth() / 2 + "]");
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public Camera camera = new Camera();
                            public Matrix matrix = new Matrix();

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                cameraAni(matrix, camera, (Float) animation.getAnimatedValue(), 0, 0, 0, 0, 0, iv, item);
                                iv.setImageMatrix(matrix);

                            }

                        }).setFloatValues(0, getAnInt(cb) * iv.getWidth() / 2).start();

                        break;
                    case "tY":
                        tv_range.setText("value:[0," + getAnInt(cb) * iv.getHeight() / 2 + "]");
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public Camera camera = new Camera();
                            public Matrix matrix = new Matrix();

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                cameraAni(matrix, camera, 0, (Float) animation.getAnimatedValue(), 0, 0, 0, 0, iv, item);
                                iv.setImageMatrix(matrix);

                            }

                        }).setFloatValues(0, getAnInt(cb) * iv.getWidth() / 2).start();
                        break;
                    case "tZ":
                        tv_range.setText("value:[0," + getAnInt(cb) * iv.getHeight() / 2 + "]");
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public Camera camera = new Camera();
                            public Matrix matrix = new Matrix();

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                cameraAni(matrix, camera, 0, 0, (Float) animation.getAnimatedValue(), 0, 0, 0, iv, item);
                                iv.setImageMatrix(matrix);

                            }

                        }).setFloatValues(0, getAnInt(cb) * iv.getWidth() / 2).start();
                        break;
                    case "rX":
                        tv_range.setText("value:[0," + getAnInt(cb) * 360 + "]");
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public Camera camera = new Camera();
                            public Matrix matrix = new Matrix();

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                cameraAni(matrix, camera, 0, 0, 0, (Float) animation.getAnimatedValue(), 0, 0, iv, item);
                                iv.setImageMatrix(matrix);

                            }

                        }).setFloatValues(0, getAnInt(cb) * 360).start();
                        break;
                    case "rY":
                        tv_range.setText("value:[0," + getAnInt(cb) * 360 + "]");
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public Camera camera = new Camera();
                            public Matrix matrix = new Matrix();

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                cameraAni(matrix, camera, 0, 0, 0, 0, (Float) animation.getAnimatedValue(), 0, iv, item);
                                iv.setImageMatrix(matrix);

                            }

                        }).setFloatValues(0, getAnInt(cb) * 360).start();
                        break;
                    case "rZ":
                        tv_range.setText("value:[0," + getAnInt(cb) * 360 + "]");
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public Camera camera = new Camera();
                            public Matrix matrix = new Matrix();

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                cameraAni(matrix, camera, 0, 0, 0, 0, 0, (Float) animation.getAnimatedValue(), iv, item);
                                iv.setImageMatrix(matrix);

                            }

                        }).setFloatValues(0, getAnInt(cb) * 360).start();
                        break;
                }

            }

            private int getAnInt(CheckBox cb) {
                return cb.isChecked() ? -1 : 1;
            }

            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.item_ani;
            }
        });
    }

    private void view() {
        gvView.setAdapter(new QuickAdapter<String>(_AniActivity.this, ArraysUtil.asList(propertys)) {

            @Override
            public void fillData(Helper<String> helper, final String item, boolean itemChanged, int layoutId) {


                helper.setText(R.id.tv_property, item);

                final CheckBox cb = helper.getView(R.id.cb);
                final TextView tv_range = helper.getView(R.id.tv_range);
                final View iv = helper.getView(R.id.iv);
                final ObjectAnimatorProxy ob = ObjectAnimatorHelper.ofFloatProxy(iv, "translationX", 0, 300)
                        .setRepeatCount(ValueAnimator.INFINITE).setDuration(2500);
                openAni(item, ob, cb, tv_range, iv);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        openAni(item, ob, cb, tv_range, iv);
                    }
                });

            }

            private void openAni(String item, final ObjectAnimatorProxy valueAnimator, final CheckBox cb,
                                 final TextView tv_range, final View iv) {
                if (valueAnimator.isRunning())
                    valueAnimator.end();
                switch (item) {
                    case "tX":
                        iv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_range.setText("value:[0," + getAnInt(cb) * iv.getWidth() / 2 + "]");
                                valueAnimator.setTarget(iv).setPropertyName("translationX").setFloatValues(0, getAnInt(cb) * iv.getWidth() / 2).start();
                            }
                        });

                        break;
                    case "tY":
                        iv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_range.setText("value:[0," + getAnInt(cb) * iv.getHeight() / 2 + "]");
                                valueAnimator.setTarget(iv).setPropertyName("translationY").setFloatValues(0, getAnInt(cb) * iv.getWidth() / 2).start();
                            }
                        });
                        break;
                    case "tZ":

                        break;
                    case "rX":
                        tv_range.setText("value:[0," + getAnInt(cb) * 360 + "]");
                        valueAnimator.setPropertyName("rotationX").setFloatValues(0, getAnInt(cb) * 360).start();
                        break;
                    case "rY":
                        tv_range.setText("value:[0," + getAnInt(cb) * 360 + "]");
                        valueAnimator.setPropertyName("rotationY").setFloatValues(0, getAnInt(cb) * 360).start();
                        break;
                    case "rZ":
                        tv_range.setText("value:[0," + getAnInt(cb) * 360 + "]");
                        valueAnimator.setPropertyName("rotation").setFloatValues(0, getAnInt(cb) * 360).start();
                        break;
                }

            }

            private int getAnInt(CheckBox cb) {
                return cb.isChecked() ? -1 : 1;
            }

            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.item_ani;
            }
        });
    }

    @Override
    public void setListener() {

    }

}
