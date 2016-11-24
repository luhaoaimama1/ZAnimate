package zone.com.zanimate.camera;

/**
 * Created by Administrator on 2016/9/16.
 */
public class LayerParent {
    // 规范: LayerParent.location(location).ro.ro.ro
    float x, y, z;
    float rX_Degrees, rY_Degrees, rZ_Degrees;

    private LayerParent() {
    }

    /**
     *     被围绕的点 类似于AE的 null object;
     */
    public static LayerParent location(float x, float y, float z) {
        LayerParent parent = new LayerParent();
        parent.x = x;
        parent.y = y;
        parent.z = z;
        return parent;
    }

    public LayerParent rotationX(float degrees) {
        rX_Degrees = degrees;
        return this;
    }

    public LayerParent rotationY(float degrees) {
        rY_Degrees = degrees;
        return this;

    }

    public LayerParent rotationZ(float degrees) {
        rZ_Degrees = degrees;
        return this;
    }

}
