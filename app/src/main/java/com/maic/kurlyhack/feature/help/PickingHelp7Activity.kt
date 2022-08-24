package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityPickingHelp7Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp7Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp7Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp7Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(R.layout.activity_picking_help7)
    }

    private fun initEventListener() {
        binding.tvPickHelp7.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp7Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp7.setOnTouchListener(object: OnSwipeTouchListener(this@PickingHelp7Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp7Activity, PickingHelp4Activity::class.java))
            }
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp7Activity, DasHelp2Activity::class.java))
            }
        })
    }
}