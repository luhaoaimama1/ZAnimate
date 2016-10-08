package zone.com.zanimate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import and.base.activity.BaseFragmentActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import zone.com.zanimate._3d.View_3D;
import zone.com.zanimate.camera.Layer;
import zone.com.zanimate.camera.LayerParent;
import zone.com.zanimate.entity.Property;
import zone.com.zanimate.fragments.LayerFragment;

/**
 * Created by fuzhipeng on 16/10/4.
 */

public class _3DActivity extends BaseFragmentActivity {

    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.iv)
    public View_3D iv;
    private Property layerProperty, layerParent_Property;

    @Override
    public void setContentView() {
        setContentView(R.layout.a_3d);
        ButterKnife.bind(this);
        vp.setAdapter(new MyPagerAdapter(this.getSupportFragmentManager()));
        recurrenceClipChildren(iv,false);
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

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            LayerFragment fragment = new LayerFragment();
            switch (position) {
                case 0:
                    return fragment;
                case 1:
                    fragment.isParent = true;
                    return fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void layer(Property property) {
        layerProperty = property;
        refresh();
    }

    public void layerParent(Property property) {
        layerParent_Property = property;
        refresh();
    }

    private void refresh() {
        if (layerParent_Property == null)
            layerParent_Property = new Property();
        if (layerProperty == null)
            layerProperty = new Property();
        iv.invalidate(
                Layer.setPivot(layerProperty.getX(), layerProperty.getY())
                        .relativeZPosition(layerProperty.getZ()) //位置 -1600+800=-800 围绕-1600 旋转
                        .rotationX(layerProperty.getxR())
                        .rotationY(layerProperty.getyR())
                        .rotationZ(layerProperty.getzR())
                        .attach(

                                LayerParent
                                        .location(layerParent_Property.getX(), layerParent_Property.getY(),
                                                layerParent_Property.getZ())
                                        .rotationX(layerParent_Property.getxR())
                                        .rotationY(layerParent_Property.getyR())
                                        .rotationZ(layerParent_Property.getzR())
                        ));
    }
    //递归view 所有parent 知道 rootView  设置clipClildren;
    public static void recurrenceClipChildren(View view, boolean clipChildren) {
        ViewGroup parentView = (ViewGroup) view.getParent();
        ViewGroup rootView = (ViewGroup) view.getRootView();
        while (!parentView.equals(rootView)) {
            parentView.setClipChildren(clipChildren);
            parentView = (ViewGroup) parentView.getParent();
        }
        rootView.setClipChildren(clipChildren);
    }


}
