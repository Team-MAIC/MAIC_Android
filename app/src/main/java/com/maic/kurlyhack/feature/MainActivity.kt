package com.maic.kurlyhack.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityMainBinding
import com.maic.kurlyhack.feature.picking.SelectPickingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListener()
    }

    private fun initClickListener() {
        // TODO: 작업내용이 피킹인지 다스인지 확인하고 화면 연결
        binding.btnMainStart.setOnClickListener {
            startActivity(Intent(this@MainActivity, SelectPickingActivity::class.java))
        }
// startActivity(Intent(this@MainActivity, DasActivity::class.java))
    }
}
