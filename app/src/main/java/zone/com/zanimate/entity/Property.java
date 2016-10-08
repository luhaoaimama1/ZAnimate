package zone.com.zanimate.entity;

/**
 * Created by fuzhipeng on 16/10/4.
 */

public class Property {
    private int x;
    private int y;
    private int z;
    private int xR;
    private int yR;
    private int zR;
    private boolean alignCenter;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getxR() {
        return xR;
    }

    public void setxR(int xR) {
        this.xR = xR;
    }

    public int getyR() {
        return yR;
    }

    public void setyR(int yR) {
        this.yR = yR;
    }

    public int getzR() {
        return zR;
    }

    public void setzR(int zR) {
        this.zR = zR;
    }

    public boolean isAlignCenter() {
        return alignCenter;
    }

    public void setAlignCenter(boolean alignCenter) {
        this.alignCenter = alignCenter;
    }
}
