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
            case R.id.tv_2d_rebound:
                startActivity(new Intent(this,_ReboundActivity.class));
                break;
            case R.id.tv_imit:
                startActivity(new Intent(this,_ImitationActivity.class));
                break;
            case R.id.tv_ani:
                startActivity(new Intent(this,_AniActivity.class));
                break;
            case R.id.tv_ani2:
                startActivity(new Intent(this,_3DActivity_.class));
                break;
        }
    }


}
