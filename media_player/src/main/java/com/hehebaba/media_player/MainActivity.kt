package com.hehebaba.media_player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoPath = "android.resource://$packageName/${R.raw.dk}"
        videoView.setVideoPath(videoPath)
        videoView.setMediaController(MediaController(this))
        videoView.setOnPreparedListener {
            it.start()
        }
    }
}
