package io.github.halochn.activity_prictise

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

class SingleInstanceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
    }

    fun startSelf(view: View) {
        Intent(this, SingleInstanceActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }
}
