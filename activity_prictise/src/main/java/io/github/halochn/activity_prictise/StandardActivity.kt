package io.github.halochn.activity_prictise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

class StandardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard)
    }

    fun startSelf(view: View) {
        Intent(this, SingleTopActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }
}