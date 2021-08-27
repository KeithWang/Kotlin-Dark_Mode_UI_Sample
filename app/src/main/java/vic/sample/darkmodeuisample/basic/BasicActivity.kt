package vic.sample.darkmodeuisample.basic

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import vic.sample.darkmodeuisample.BuildConfig

open class BasicActivity : AppCompatActivity() {

    private var mToast: Toast? = null
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    private fun initParams() {
        mContext = this
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT)
    }

    fun callToast(str: String, isLong: Boolean) {
        mToast?.cancel()

        mToast =
            Toast.makeText(mContext, str, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)

        mToast?.setGravity(Gravity.CENTER, 0, 0)

        mToast?.show()
    }

    fun Activity.transparentStatusBar() {
        if (Build.VERSION.SDK_INT in 23..29) {
            window.statusBarColor = Color.TRANSPARENT
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LAYOUT_STABLE

        } else if (Build.VERSION.SDK_INT >= 30) {
            window.statusBarColor = Color.TRANSPARENT
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
}