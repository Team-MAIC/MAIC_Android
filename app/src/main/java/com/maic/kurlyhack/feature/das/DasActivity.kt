package com.maic.kurlyhack.feature.das

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.maic.kurlyhack.R
import com.maic.kurlyhack.data.local.DasData
import com.maic.kurlyhack.databinding.ActivityDasBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.feature.picking.PickingBarCodeActivity

class DasActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityDasBinding
    private lateinit var dasAdapter: DasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasBinding.inflate(layoutInflater)

        initAdapter()
        initBtnListener()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        dasAdapter = DasAdapter()

        binding.rvDas.adapter = dasAdapter

        dasAdapter.dasList.addAll(
            listOf(
                DasData("하양", "BOX2", "", "0g", "(0개)"),
                DasData("검정", "BOX3", "수박", "10g", "(1개)"),
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

    private fun initBtnListener() {
        binding.ivDasFilter.setOnClickListener {
            val dialog = FilterDialog(this)
            dialog.showDialog(this)
        }

        binding.btnDasBarcode.setOnClickListener {
            startActivity(Intent(this@DasActivity, DasBarcodeActivity::class.java))
        }
    }

    override fun onClick(value: String) {
        // TODO: value값으로
        Toast.makeText(this, "$value 눌림", Toast.LENGTH_SHORT).show()
    }

    override fun onListClick(value: ArrayList<String>) {
    }
}
