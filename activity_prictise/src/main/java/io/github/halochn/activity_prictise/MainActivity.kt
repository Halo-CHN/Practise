package io.github.halochn.activity_prictise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openStandard(view: View) {
        Intent(this, StandardActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }

    fun openSingleTop(view: View) {
        Intent(this, SingleTopActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }

    fun openSingleTask(view: View) {
        Intent(this, SingleTaskActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }

    fun openSingleInstance(view: View) {
        Intent(this, SingleInstanceActivity::class.java).also {
            ActivityCompat.startActivity(this, it, null)
        }
    }
}
