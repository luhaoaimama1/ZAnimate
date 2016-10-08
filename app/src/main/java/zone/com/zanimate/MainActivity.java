package zone.com.zanimate;

import android.content.Intent;
import android.view.View;

import and.base.activity.BaseActivity;

/**
 * Created by fuzhipeng on 16/10/4.
 */

public class MainActivity extends BaseActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.a_main);
    }

    @Override
    public void findIDs() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_2d:
                startActivity(new Intent(this,_2DActivity.class));
                break;
            case R.id.tv_3d2:
                startActivity(new Intent(this,_3DActivity.class));
                break;
        }
    }


}
