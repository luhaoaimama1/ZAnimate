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

public class Layer {

    private float tx, ty, tz;
    private float rx, ry, rz;
    private Layer parent;

    public Layer() {
    }


    public Layer translate(float x, float y, float z) {
        tx=x;ty=y;tz=z;
        return this;
    }


    public Layer rotate(float x, float y, float z) {
        rx=x;ry=y;rz=z;
        return this;
    }
    public Layer bindParent(Layer parent) {
        this.parent = parent;
        return this;
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
    public Layer getMatrix(Matrix matrix , CameraCorrect cameraFinal) {

        List<Layer> parentlist = new ArrayList<>();
        parentlist.add(this);

        Layer rootParent = parent;
        while (rootParent!=null) {
            parentlist.add(rootParent);
            rootParent = rootParent.parent;
        }


        cameraFinal.save();
        for (int i = parentlist.size() - 1; i >= 0; i--) {
            Layer layer = parentlist.get(i);
            cameraFinal.translate(layer.tx, layer.ty, layer.tz);
            cameraFinal.rotate(layer.rx, layer.ry, layer.rz);
        }

        cameraFinal.getMatrix(matrix);
        cameraFinal.restore();
        return this;
    }

    public float getTranslateX() {
        return tx;
    }

    public void translateX(float tx) {
        this.tx = tx;
    }

    public float getTranslateY() {
        return ty;
    }

    public void translateY(float ty) {
        this.ty = ty;
    }

    public float getTranslateZ() {
        return tz;
    }

    public void translateZ(float tz) {
        this.tz = tz;
    }

    public float getRorateX() {
        return rx;
    }

    public void rorateX(float rx) {
        this.rx = rx;
    }

    public float getRorateY() {
        return ry;
    }

    public void rorateY(float ry) {
        this.ry = ry;
    }

    public float getRorateZ() {
        return rz;
    }

    public void rorateZ(float rz) {
        this.rz = rz;
    }
}
