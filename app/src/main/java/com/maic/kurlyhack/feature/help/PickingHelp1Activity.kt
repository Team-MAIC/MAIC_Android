package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityPickingHelp1Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp1Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(R.layout.activity_picking_help1)
    }

    private fun initEventListener() {
        binding.tvPickHelp1.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp1Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp1.setOnTouchListener(object: OnSwipeTouchListener(this@PickingHelp1Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp1Activity, PickingHelp2Activity::class.java))
            }
        })
    }
}