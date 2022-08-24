package com.maic.kurlyhack.feature.das

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.request.RequestDasSubscribe
import com.maic.kurlyhack.data.remote.request.RequestMapping
import com.maic.kurlyhack.data.remote.response.BasketItemData
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
    var roundId = 0
    var filterId = 0
    var centerRoundNumber = 0
    var workerId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasBinding.inflate(layoutInflater)

        getData()
        initBtnListener()
        connectWebSocket()

        setContentView(binding.root)
    }

    private fun getData() {
        passage = intent.getStringExtra("area")!!.toInt()
        workerId = intent.getIntExtra("workerId", 0)
        KurlyClient.dasService.getBoxData(
            centerId,
            passage
        ).callback.onSuccess {
            binding.tvDasDetailPart.text = passage.toString() + "번 통로 : " + it.data!!.centerRoundNumber + "회차"
            if (it.code == 4001) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            } else {
                roundId = it.data!!.roundId
                centerRoundNumber = it.data.centerRoundNumber
                val mappingList = arrayListOf<Map<String, Int>>()
                for (i in 0 until it.data.baskets.size) {
                    var map = mutableMapOf<String, Int>()
                    map["clientIdx"] = i
                    map["basketNum"] = it.data.baskets[i].basketNum
                    mappingList.add(map)
                    Log.d("###", mappingList.toString())
                }

                val requestMapping = RequestMapping(
                    baskets = mappingList
                )

                KurlyClient.dasService.postMapping(
                    centerId,
                    passage,
                    requestMapping
                ).callback.onSuccess {
                    initAdapter(0)
                }.enqueue()
            }
        }.enqueue()
    }

    private fun initAdapter(i: Int) {
        dasAdapter = DasAdapter(this)
        binding.rvDas.adapter = dasAdapter
        var resultList = mutableListOf<BasketItemData>()

        KurlyClient.dasService.getDasData(
            roundId
        ).callback.onSuccess { it ->
            with(binding) {
                for (i in 0 until it.data!!.colors.size) {
                    when (it.data.colors[i].color) {
                        "RED" -> tvDasRed.text = it.data.colors[i].productName
                        "YELLOW" -> tvDasYellow.text = it.data.colors[i].productName
                        "GREEN" -> tvDasGreen.text = it.data.colors[i].productName
                        "BLUE" -> tvDasBlue.text = it.data.colors[i].productName
                    }
                }
            }
            when (i) {
                0 -> {
                    resultList = it.data!!.baskets
                }
                1 -> {
                    resultList = it.data!!.baskets.filter {
                        it.todo != null
                    }.toMutableList()
                }
                2 -> {
                    resultList = it.data!!.baskets.filter {
                        it.todo != null
                    }.toMutableList().filter {
                        it.todo.color == "RED"
                    }.toMutableList().filter {
                        it.todo.status == "READY"
                    }.toMutableList()
                }
                3 -> {
                    resultList = it.data!!.baskets.filter {
                        it.todo != null
                    }.toMutableList().filter {
                        it.todo.color == "YELLOW"
                    }.toMutableList().filter {
                        it.todo.status == "READY"
                    }.toMutableList()
                }
                4 -> {
                    resultList = it.data!!.baskets.filter {
                        it.todo != null
                    }.toMutableList().filter {
                        it.todo.color == "GREEN"
                    }.toMutableList().filter {
                        it.todo.status == "READY"
                    }.toMutableList()
                }
                5 -> {
                    resultList = it.data!!.baskets.filter {
                        it.todo != null
                    }.toMutableList().filter {
                        it.todo.color == "BLUE"
                    }.toMutableList().filter {
                        it.todo.status == "READY"
                    }.toMutableList()
                }
                6 -> {
                    resultList = it.data!!.baskets.filter {
                        it.todo != null
                    }.toMutableList().filter {
                        it.todo.status == "WRONG"
                    }.toMutableList()
                }
            }
            dasAdapter.dasList.addAll(resultList)
            dasAdapter.notifyDataSetChanged()
        }.enqueue()
    }

    private fun initBtnListener() {
        binding.ivDasFilter.setOnClickListener {
            val dialog = FilterDialog(this)
            dialog.showDialog(this)
        }

        binding.btnDasBarcode.setOnClickListener {
            val intent = Intent(this@DasActivity, DasBarcodeActivity::class.java)
            intent.putExtra("roundId", roundId)
            intent.putExtra("passage", passage)
            intent.putExtra("centerId", centerId)
            startActivity(intent)
        }

        binding.ivDasMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }

    @SuppressLint("CheckResult")
    private fun connectWebSocket() {

        val url = "wss://project-maic.com/wss/websocket" // 소켓에 연결하는 엔드포인트가 /socket일때 다음과 같음
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
//                runOnUiThread {
//                    initAdapter(filterId)
//                }
            }
    }

    override fun onClick(value: String) {
        when (value) {
            "ALL" -> filterId = 0
            "ONGOING" -> filterId = 1
            "RED" -> filterId = 2
            "YELLOW" -> filterId = 3
            "GREEN" -> filterId = 4
            "BLUE" -> filterId = 5
            "BLACK" -> filterId = 6
        }
        initAdapter(filterId)
    }

    override fun onListClick(value: ArrayList<String>) {
        val intent = Intent(this, CountErrorActivity::class.java)
        intent.putExtra("info", value)
        intent.putExtra("passage", passage)
        intent.putExtra("roundId", roundId)
        intent.putExtra("centerRoundNumber", centerRoundNumber)
        intent.putExtra("workerId", workerId)
        startActivity(intent)
    }
}
