package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.PartData
import com.maic.kurlyhack.databinding.ActivitySelectPickingBinding
import com.maic.kurlyhack.feature.OnItemClick

class SelectPickingActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivitySelectPickingBinding
    private lateinit var selectPickingAdapter: SelectPickingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPickingBinding.inflate(layoutInflater)

        initAdapter()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        selectPickingAdapter = SelectPickingAdapter(this)

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

    override fun onClick(value: String) {
        val intent = Intent(this, PickingActivity::class.java)
        intent.putExtra("pickingPart", value)
        startActivity(intent)
    }
}
