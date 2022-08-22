package com.maic.kurlyhack.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityMainBinding
import com.maic.kurlyhack.feature.das.DasActivity
import com.maic.kurlyhack.feature.picking.SelectPickingActivity
import com.maic.kurlyhack.util.showDrawer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var workerId = 0
    var isPick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        initClickListener()
    }

    private fun getData() {
        workerId = intent.getIntExtra("workerId", 0)
        isPick = intent.getBooleanExtra("isPick", false)
        binding.tvMainPart.text = intent.getStringExtra("workerPart")
    }

    private fun initClickListener() {
        // TODO: 작업내용이 피킹인지 다스인지 확인하고 화면 연결
        binding.btnMainStart.setOnClickListener {
            if (isPick) {
                startActivity(Intent(this@MainActivity, SelectPickingActivity::class.java))
            } else {
                startActivity(Intent(this@MainActivity, DasActivity::class.java))
            }
        }

        binding.ivMainMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }
}
