package zone.com.zanimate.camera;

import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.List;

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

public class ZLayerFinal {

//    设计规范 layer.setPoivet.relativeZPosition.ro.ro.ro.atttch（parent(location).ro.ro）


//    设计规范 layer.setPoivet(x,y,z).position(x,y,z).ro.ro.ro.atttch（parent(location).ro.r
    //


    private float tx, ty, tz;
    private float rx, ry, rz;
    private ZLayerFinal parent;

    public ZLayerFinal() {
    }


    public ZLayerFinal translate(float x, float y, float z) {
        tx=x;ty=y;tz=z;
        return this;
    }


    public ZLayerFinal rotate(float x, float y, float z) {
        rx=x;ry=y;rz=z;
        return this;
    }
    public ZLayerFinal bindParent(ZLayerFinal parent) {
        this.parent = parent;
        return this;
    }

    public ZLayerFinal getMatrix(Matrix matrix ,ZCameraFinal cameraFinal) {
//        ZCameraFinal cameraFinal = new ZCameraFinal(100, 100);
//
//        cameraFinal.translate(parent.parent.tx, parent.parent.ty, parent.parent.tz);
//        cameraFinal.rotate(parent.parent.rx, parent.parent.ry, parent.parent.rz);
//
//        cameraFinal.translate(parent.tx, parent.ty, parent.tz);
//        cameraFinal.rotate(parent.rx, parent.ry, parent.rz);
//
//        cameraFinal.translate(tx, ty, tz);
//        cameraFinal.rotate(rx, ry, rz);

        List<ZLayerFinal> parentlist = new ArrayList<>();
        parentlist.add(this);

        ZLayerFinal rootParent = parent;
        while (rootParent!=null&&rootParent.parent != null) {
            rootParent = rootParent.parent;
            parentlist.add(rootParent);
        }


        cameraFinal.save();
        for (int i = parentlist.size() - 1; i >= 0; i--) {
            ZLayerFinal layer = parentlist.get(i);
            cameraFinal.translate(layer.tx, layer.ty, layer.tz);
            cameraFinal.rotate(layer.rx, layer.ry, layer.rz);
        }

        cameraFinal.getMatrix(matrix);
        cameraFinal.restore();
        return this;
    }
}
