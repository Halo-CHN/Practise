package io.github.halochn.activity_prictise

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.core.app.ActivityCompat

class SingleTopActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_top)
    }

    fun startSelf(view: View) {
        Intent(this, SingleTopActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }
}