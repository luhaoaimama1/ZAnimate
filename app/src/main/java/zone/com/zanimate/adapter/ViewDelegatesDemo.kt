package com.qyzc.medical.smartbodydevice.ui.adapter.delegate.core

import android.view.View
import com.zone.adapter3kt.delegate.ViewDelegate
import zone.com.zanimate.adapter.HolderExDemoImpl

/**
 *[2019/3/5] by Zone
 */
abstract class ViewDelegatesDemo<T> : ViewDelegate<T, HolderExDemoImpl>() {
    override fun createHolder(view: View): HolderExDemoImpl {
        return HolderExDemoImpl(view)
    }
}