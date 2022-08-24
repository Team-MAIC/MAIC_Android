package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityPickingHelp8Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp8Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp8Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(R.layout.activity_picking_help8)
    }

    private fun initEventListener() {
        binding.btnPickHelp.setOnClickListener {
            finish()
        }

        binding.clPickHelp8.setOnTouchListener(object: OnSwipeTouchListener(this@PickingHelp8Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp8Activity, DasHelp2Activity::class.java))
            }
        })
    }
}