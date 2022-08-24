package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp4Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp4Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(R.layout.activity_das_help4)
    }

    private fun initEventListener() {
        binding.tvDasHelp4.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp4Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp4.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp4Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp4Activity, DasHelp5Activity::class.java))
            }
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp4Activity, DasHelp3Activity::class.java))
            }
        })
    }
}