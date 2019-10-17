package zone.com.zanimate.adapter

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import com.zone.adapter3kt.holder.BaseHolder

open class HolderExDemoImpl : BaseHolder<HolderExDemoImpl> {
    constructor(view: View) : super(view)
    constructor(baseHolder: BaseHolder<*>) : super(baseHolder)

    override fun getReturnValue(): HolderExDemoImpl = this

    fun ex2(): HolderExDemoImpl {
        return getReturnValue()
    }

}
