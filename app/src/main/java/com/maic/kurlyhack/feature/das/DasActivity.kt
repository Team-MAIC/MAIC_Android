package com.maic.kurlyhack.feature.das

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.DasData
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.request.RequestDasSubscribe
import com.maic.kurlyhack.data.remote.request.RequestSubscribe
import com.maic.kurlyhack.databinding.ActivityDasBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent

class DasActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityDasBinding
    private lateinit var dasAdapter: DasAdapter
    var passage = 0
    var centerId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasBinding.inflate(layoutInflater)

        getData()
        initAdapter()
        initBtnListener()
        connectWebSocket()

        setContentView(binding.root)
    }

    private fun getData() {
        passage = intent.getStringExtra("area")!!.toInt()
        centerId = intent.getIntExtra("centerId", 0)
        KurlyClient.dasService.getBoxData(
            centerId,
            passage
        ).callback.onSuccess {
            binding.tvDasDetailPart.text = passage.toString() + "번 통로 : " + centerId + "회차"
            if (it.code == 4001) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }.enqueue()
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

        binding.ivDasMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }

    @SuppressLint("CheckResult")
    private fun connectWebSocket() {

        val url = "ws://192.168.100.33:8080/ws/websocket" // 소켓에 연결하는 엔드포인트가 /socket일때 다음과 같음
        val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

        stompClient.lifecycle().subscribe { lifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.d("OPEND", "!!")
                }
                LifecycleEvent.Type.CLOSED -> {
                    Log.d("CLOSED", "!!")
                }
                LifecycleEvent.Type.ERROR -> {
                    Log.d("ERROR", "!!")
                    Log.d("CONNECT ERROR", lifecycleEvent.exception.toString())
                }
                else -> {
                    Log.d("ELSE", lifecycleEvent.message)
                }
            }
        }

        stompClient.connect()

        val requestDasSubscribe = RequestDasSubscribe(
            centerId = centerId,
            passage = passage
        )

        KurlyClient.dasService.postDasSubscribe(
            requestDasSubscribe
        ).callback.onSuccess {
            Log.d("###", "post완료")
        }.enqueue()

        stompClient.topic("/sub/das/todos/$centerId/$passage")
            .subscribe {
                runOnUiThread {
                    Log.d("###", "성공")
                    //initAdapter(mCategory)
                }
            }
    }

    override fun onClick(value: String) {
        // TODO: value값으로
        Toast.makeText(this, "$value 눌림", Toast.LENGTH_SHORT).show()
    }

    override fun onListClick(value: ArrayList<String>) {
    }
}
