package com.maic.kurlyhack.feature.help

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp2Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp2Binding.inflate(layoutInflater)
        initEventListener()

        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvDasHelp2.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp2Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp2.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp2Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp2Activity, DasHelp3Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp2Activity, DasHelp1Activity::class.java))
            }
        })
    }
}
