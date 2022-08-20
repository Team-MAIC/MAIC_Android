package com.maic.kurlyhack.feature.picking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.PartData
import com.maic.kurlyhack.databinding.ActivitySelectPickingBinding

class SelectPickingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectPickingBinding
    private lateinit var selectPickingAdapter: SelectPickingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPickingBinding.inflate(layoutInflater)

        initAdapter()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        selectPickingAdapter = SelectPickingAdapter()

        binding.rvSelectPickingPart.adapter = selectPickingAdapter

        selectPickingAdapter.partList.addAll(
            listOf(
                PartData("1회차", true),
                PartData("2회차", false),
                PartData("3회차", false)
            )
        )
        selectPickingAdapter.notifyDataSetChanged()
    }
}
