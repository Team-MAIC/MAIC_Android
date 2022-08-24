package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp3Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp3Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(R.layout.activity_das_help3)
    }

    private fun initEventListener() {
        binding.tvDasHelp3.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp3Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp3.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp3Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp3Activity, DasHelp3Activity::class.java))
            }
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp3Activity, DasHelp2Activity::class.java))
            }
        })
    }
}