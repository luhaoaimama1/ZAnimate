package zone.com.zanimate

import android.content.Intent
import android.view.View

import com.zone.lib.base.activity.BaseAppCompatActivity

/**
 * Created by fuzhipeng on 16/10/4.
 */

class MainActivity : BaseAppCompatActivity() {

    override fun setContentView() {
        setContentView(R.layout.a_main)
    }

    override fun findIDs() {

    }

    override fun initData() {

    }

    override fun setListener() {

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v!!.id) {
            R.id.tv_2d -> startActivity(Intent(this, _2DActivity::class.java))
            R.id.tv_2d_rebound -> startActivity(Intent(this, _ReboundActivity::class.java))
            R.id.tv_imit -> startActivity(Intent(this, _ImitationActivity::class.java))
            R.id.tv_ani -> startActivity(Intent(this, _AniActivity::class.java))
            R.id.tv_ani2 -> startActivity(Intent(this, _3DActivity_::class.java))
        }
    }


}
