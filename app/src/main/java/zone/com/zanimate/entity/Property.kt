package zone.com.zanimate.entity

/**
 * Created by fuzhipeng on 16/10/4.
 */

class Property {
    var x: Int = 0
    var y: Int = 0
    var z: Int = 0
    private var xR: Int = 0
    private var yR: Int = 0
    private var zR: Int = 0
    var isAlignCenter: Boolean = false

    fun getxR(): Int {
        return xR
    }

    fun setxR(xR: Int) {
        this.xR = xR
    }

    fun getyR(): Int {
        return yR
    }

    fun setyR(yR: Int) {
        this.yR = yR
    }

    fun getzR(): Int {
        return zR
    }

    fun setzR(zR: Int) {
        this.zR = zR
    }
}
