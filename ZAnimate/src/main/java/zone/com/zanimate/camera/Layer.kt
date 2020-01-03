package zone.com.zanimate.camera

import android.graphics.Matrix

import java.util.ArrayList

/**
 * MIT License
 * Copyright (c) [2017] [Zone]
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

class Layer {

    var translateX: Float = 0.toFloat()
        private set
    var translateY: Float = 0.toFloat()
        private set
    var translateZ: Float = 0.toFloat()
        private set
    var rorateX: Float = 0.toFloat()
        private set
    var rorateY: Float = 0.toFloat()
        private set
    var rorateZ: Float = 0.toFloat()
        private set
    private var parent: Layer? = null


    fun translate(x: Float, y: Float, z: Float): Layer {
        translateX = x
        translateY = y
        translateZ = z
        return this
    }


    fun rotate(x: Float, y: Float, z: Float): Layer {
        rorateX = x
        rorateY = y
        rorateZ = z
        return this
    }

    fun bindParent(parent: Layer): Layer {
        this.parent = parent
        return this
    }

    //        CameraCorrect cameraFinal = new CameraCorrect(100, 100);
    //
    //        cameraFinal.translate(parent.parent.tx, parent.parent.ty, parent.parent.tz);
    //        cameraFinal.rotate(parent.parent.rx, parent.parent.ry, parent.parent.rz);
    //
    //        cameraFinal.translate(parent.tx, parent.ty, parent.tz);
    //        cameraFinal.rotate(parent.rx, parent.ry, parent.rz);
    //
    //        cameraFinal.translate(tx, ty, tz);
    //        cameraFinal.rotate(rx, ry, rz);
    fun getMatrix(matrix: Matrix, cameraFinal: CameraCorrect): Layer {

        val parentlist = ArrayList<Layer>()
        parentlist.add(this)

        var rootParent = parent
        while (rootParent != null) {
            parentlist.add(rootParent)
            rootParent = rootParent.parent
        }


        cameraFinal.save()
        for (i in parentlist.indices.reversed()) {
            val layer = parentlist[i]
            cameraFinal.translate(layer.translateX, layer.translateY, layer.translateZ)
            cameraFinal.rotate(layer.rorateX, layer.rorateY, layer.rorateZ)
        }

        cameraFinal.getMatrix(matrix)
        cameraFinal.restore()
        return this
    }

    fun translateX(tx: Float) {
        this.translateX = tx
    }

    fun translateY(ty: Float) {
        this.translateY = ty
    }

    fun translateZ(tz: Float) {
        this.translateZ = tz
    }

    fun rorateX(rx: Float) {
        this.rorateX = rx
    }

    fun rorateY(ry: Float) {
        this.rorateY = ry
    }

    fun rorateZ(rz: Float) {
        this.rorateZ = rz
    }
}
