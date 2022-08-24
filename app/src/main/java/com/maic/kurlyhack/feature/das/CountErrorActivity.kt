package com.maic.kurlyhack.feature.das

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.request.RequestMessage
import com.maic.kurlyhack.databinding.ActivityCountErrorBinding
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class CountErrorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountErrorBinding
    var productId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountErrorBinding.inflate(layoutInflater)

        getData()
        initBtnClickListener()

        setContentView(binding.root)
    }

    private fun getData() {
        var info = intent.getStringArrayListExtra("info")
        var passage = intent.getIntExtra("passage", 0)
        binding.tvCountErrorPart.text = passage.toString() + "번 통로 " + "BOX " + info!![0]
        binding.tvCountErrorItem.text = info[1]
        binding.etCountError.hint = info[2]
        productId = info[3].toInt()
        Glide.with(this).load("https://img.project-maic.com/product/" + info[4]).into(binding.ivCountError)
        binding.tvCountErrorDes.text = "예상 부족 수량 : " + info[2] + "개"
    }

    private fun initBtnClickListener() {
        binding.btnCountTransmit.setOnClickListener {
            val requestMessage = RequestMessage(
                roundId = intent.getIntExtra("roundId", 0),
                productId = productId,
                productName = binding.tvCountErrorItem.text.toString(),
                centerRoundNumber = intent.getIntExtra("centerRoundNumber", 0),
                amount = binding.etCountError.hint.toString().toInt()
            )
            Log.d("###", requestMessage.toString())

            val count = binding.etCountError.text.toString()
            if (count.isNotEmpty()) {
                KurlyClient.messageService.postMessages(
                    intent.getIntExtra("workerId", 0),
                    requestMessage
                ).callback.onSuccess {
                    Toast.makeText(this, count + "개 전송", Toast.LENGTH_SHORT).show()
                    finish()
                }.enqueue()
            } else {
                Toast.makeText(this, "수량을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivCountErrorMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivCountErrorBack.setOnClickListener {
            finish()
        }
    }
}
