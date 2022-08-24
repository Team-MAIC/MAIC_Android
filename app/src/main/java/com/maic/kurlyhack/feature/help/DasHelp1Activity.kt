package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp1Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp1Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(R.layout.activity_das_help1)
    }

    private fun initEventListener() {
        binding.tvDasHelp1.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp1Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp1.setOnTouchListener(object: OnSwipeTouchListener(this@DasHelp1Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp1Activity, DasHelp2Activity::class.java))
            }
        })
    }
}