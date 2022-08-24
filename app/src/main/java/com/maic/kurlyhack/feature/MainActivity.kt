package com.maic.kurlyhack.feature

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityMainBinding
import com.maic.kurlyhack.feature.das.DasActivity
import com.maic.kurlyhack.feature.picking.SelectPickingActivity
import com.maic.kurlyhack.util.showDrawer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var workerId = 0
    var isPick = false
    var area = ""
    var centerId = 0

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
        area = binding.tvMainPart.text.split(' ')[2]
        centerId = intent.getIntExtra("centerId", 0)
    }

    private fun initClickListener() {
        binding.btnMainStart.setOnClickListener {
            if (isPick) {
                val intent = Intent(this@MainActivity, SelectPickingActivity::class.java)
                intent.putExtra("workerId", workerId)
                intent.putExtra("workerArea", area)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@MainActivity, DasActivity::class.java)
                intent.putExtra("area", area)
                intent.putExtra("centerId", centerId)
                intent.putExtra("workerId", workerId)
                startActivity(intent)
                finish()
            }
        }

        binding.ivMainMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }
}
