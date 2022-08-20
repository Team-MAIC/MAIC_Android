package com.maic.kurlyhack.feature.das

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.DasData
import com.maic.kurlyhack.databinding.ActivityDasBinding

class DasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDasBinding
    private lateinit var dasAdapter: DasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasBinding.inflate(layoutInflater)

        initAdapter()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        dasAdapter = DasAdapter()

        binding.rvDas.adapter = dasAdapter

        dasAdapter.dasList.addAll(
            listOf(
                DasData("하양", "BOX2", "", "0g", "(0개)"),
                DasData("빨강", "BOX3", "수박", "10g", "(1개)"),
                DasData("빨강", "BOX1", "수박", "12g", "(1개)"),
                DasData("노랑", "BOX4", "사과", "25g", "(6개)"),
                DasData("초록", "BOX5", "치약", "3g", "(1개)"),
                DasData("하양", "BOX6", "", "0g", "(0개)"),
                DasData("하양", "BOX7", "", "0g", "(0개)"),
                DasData("파랑", "BOX8", "멜론", "14g", "(2개)")
            )
        )
        dasAdapter.notifyDataSetChanged()
    }
}
