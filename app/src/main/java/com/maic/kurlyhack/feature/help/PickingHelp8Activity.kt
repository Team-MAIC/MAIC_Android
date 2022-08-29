package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maic.kurlyhack.databinding.ActivityPickingHelp8Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp8Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp8Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.btnPickHelp.setOnClickListener {
            finish()
        }

        binding.clPickHelp8.setOnTouchListener(object: OnSwipeTouchListener(this@PickingHelp8Activity) {
            override fun onSwipeLeft() {
                Toast.makeText(this@PickingHelp8Activity, "마지막 페이지입니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp8Activity, PickingHelp7Activity::class.java))
            }
        })
    }
}