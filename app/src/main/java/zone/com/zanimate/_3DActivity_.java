package zone.com.zanimate;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.zone.adapter.QuickAdapter;
import com.zone.adapter.callback.Helper;

import and.base.activity.BaseActivity;
import and.base.activity.kinds.SwipeBackKind;
import and.utils.data.convert.ArraysUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zone.com.zanimate.camera.ZLayer;
import zone.com.zanimate.view.TouchProgressView;
import zone.com.zanimate.view.View_3D;

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

public class _3DActivity_ extends BaseActivity {
    @Bind(R.id.bt_first)
    Button bt_first;
    @Bind(R.id.bt_Second)
    Button bt_Second;
    @Bind(R.id.bt_Third)
    Button bt_Third;
    @Bind(R.id.gv)
    GridView gv;
    @Bind(R.id.view_3D)
    View_3D view_3D;
    private ZLayer first,second,third,now;
    Button[] buttons=new Button[3];
    private String[] propertys = new String[]{
            "tX", "tY", "tZ",
            "rX", "rY", "rZ",
    };
    private QuickAdapter<String> adapter;

    @Override
    public void updateKinds() {
        super.updateKinds();
        mKindControl.remove(SwipeBackKind.class);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.a_3d_);
        ButterKnife.bind(this);

    }

    @Override
    public void findIDs() {

    }

    @Override
    public void initData() {
        first=new ZLayer();
        second=new ZLayer();
        third=new ZLayer();
        buttons[0]=bt_first;
        buttons[1]=bt_Second;
        buttons[2]=bt_Third;
        view_3D.setZLayerFinal(first,second,third);
        gridAdapter();
        bt_first.performClick();
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.bt_first, R.id.bt_Second, R.id.bt_Third})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_first:
                click(bt_first);
                now=first;
                break;
            case R.id.bt_Second:
                click(bt_Second);
                now=second;
                break;
            case R.id.bt_Third:
                click(bt_Third);
                now=third;
                break;
        }
        adapter.notifyDataSetChanged();
    }

    private void click(Button bt_first) {
        for (Button button : buttons)
            button.setBackgroundColor(Color.WHITE);
        bt_first.setBackgroundColor(Color.YELLOW);
    }


    private void gridAdapter() {
        gv.setAdapter(adapter=new QuickAdapter<String>(_3DActivity_.this, ArraysUtil.asList(propertys)) {

            @Override
            public void fillData(Helper<String> helper, final String item, boolean itemChanged, int layoutId) {

                final TextView tv = helper.getView(R.id.tv);
                final TextView value = helper.getView(R.id.value);
                final View ll_main = helper.getView(R.id.ll_main);
                tv.setText(item+":");
                switch (item) {
                    case "tX":
                        value.setText((int)now.getTx()+"");
                        break;
                    case "tY":
                        value.setText((int)now.getTy()+"");
                        break;
                    case "tZ":
                        value.setText((int)now.getTz()+"");
                        break;
                    case "rX":
                        value.setText((int)now.getRx()%360+"");
                        break;
                    case "rY":
                        value.setText((int)now.getRy()%360+"");
                        break;
                    case "rZ":
                        value.setText((int)now.getRz()%360+"");
                        break;

                }

                final TouchProgressView tpv = helper.getView(R.id.tpv);
                ll_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value.setText(0+"");
                        tpv.getRang().start();
                        tpv.getRang().rang(0);
                    }
                });
                tpv.setRang(new TouchProgressView.Rang() {
                    public int start;

                    @Override
                    public void start() {
                        start=Integer.parseInt(value.getText().toString());
                    }

                    @Override
                    public void rang(int deltaX) {
                        int temp = start + deltaX;
                        value.setText(temp+"");
                        switch (item) {
                            case "tX":
                                now.setTx(temp);
                                break;
                            case "tY":
                                now.setTy(temp);
                                break;
                            case "tZ":
                                now.setTz(temp);
                                break;
                            case "rX":
                                now.setRx(temp);
                                break;
                            case "rY":
                                now.setRy(temp);
                                break;
                            case "rZ":
                                now.setRz(temp);
                                break;

                        }
                        view_3D.setZLayerFinal(first,second,third);
                    }
                });

            }

            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.item_cameralib;
            }
        });
    }
}
