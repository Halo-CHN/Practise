package io.github.halochn.activity_prictise

import android.app.Application
import android.content.Context

class HaloApplication : Application() {

    companion object {
        val author = "Halo-CHN"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}