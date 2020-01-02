package zone.com.zanimate._3d

import android.annotation.TargetApi
import android.graphics.Camera
import android.graphics.Matrix
import android.os.Build
import android.view.animation.Animation
import android.view.animation.Transformation


class CustomAnim : Animation() {

    private var mCenterWidth: Int = 0
    private var mCenterHeight: Int = 0
    private val mCamera = Camera()
    private var mRotateX = 0.0f
    private var mRotateY = 0.0f
    private var mRotateZ = 0.0f
    private var mX = 0.0f
    private var mY = 0.0f
    private var mZ = 0.0f

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {

        super.initialize(width, height, parentWidth, parentHeight)
        // 设置默认时长
        duration = 2000
        // 动画结束后保留状态
        fillAfter = true
        // 设置默认插值器
        //        setInterpolator(new BounceInterpolator());
        mCenterWidth = width / 2
        mCenterHeight = height / 2
    }

    // 暴露接口-设置旋转角度
    fun setRotateY(rotateY: Float) {
        mRotateY = rotateY
    }

    fun setRotateX(rotateX: Float) {
        this.mRotateX = rotateX
    }

    fun setRotateZ(rotateZ: Float) {
        this.mRotateZ = rotateZ
    }


    // 暴露接口-设置旋转角度
    fun setX(x: Float) {
        clear()
        mX = x

    }

    private fun clear() {
        mY = 0f
        mZ = mY
        mX = mZ
    }

    // 暴露接口-设置旋转角度
    fun setY(y: Float) {
        clear()
        mY = y
    }

    // 暴露接口-设置旋转角度
    fun setZ(z: Float) {
        clear()
        mZ = z
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation) {
        val matrix = t.matrix
        mCamera.save()

        mCamera.translate(mX * interpolatedTime, mY * interpolatedTime, mZ * interpolatedTime)

        mCamera.rotateX(mRotateX * interpolatedTime)
        mCamera.rotate(0f, mRotateY * interpolatedTime, 0f)
        mCamera.rotateZ(mRotateZ * interpolatedTime)

        // 将旋转变换作用到matrix上
        mCamera.getMatrix(matrix)
        mCamera.restore()
        println("mRotateX:___$mRotateX\t mRotateY:$mRotateY\t mRotateZ:$mRotateZ")
        println("camera x:" + mCamera.locationX + "\t y:" + mCamera.locationY + "\t z:" + mCamera.locationZ)
        println("Matrix:___$matrix")


        // 通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        //        matrix.preTranslate(mCenterWidth, mCenterHeight);
        //        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }
}
