package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityPickingHelp2Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp2Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvPickHelp2.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp2Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp2.setOnTouchListener(object: OnSwipeTouchListener(this@PickingHelp2Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp2Activity, PickingHelp3Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp2Activity, DasHelp1Activity::class.java))
            }
        })
    }
}