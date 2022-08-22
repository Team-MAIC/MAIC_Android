package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.response.PickingTodoData
import com.maic.kurlyhack.databinding.ActivityPickingBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer
import okhttp3.OkHttpClient
import java.util.*

class PickingActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityPickingBinding
    private lateinit var pickingAdapter: PickingAdapter
    private var pickingPart = ""
    private var myWorkerId = 0
    private lateinit var client: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingBinding.inflate(layoutInflater)

        getData()
        initAdapter(0)
        initClickListener()
        initCategory()
        connectWebSocket()

        setContentView(binding.root)
    }

    private fun getData() {
        myWorkerId = intent.getIntExtra("workerId", 0)
        pickingPart = intent.getStringExtra("pickingPart").toString()
        binding.tvPickingPart.text = pickingPart
    }

    private fun initAdapter(i: Int) {
        pickingAdapter = PickingAdapter(this)
        binding.rvPicking.adapter = pickingAdapter
        var resultList = mutableListOf<PickingTodoData>()

        KurlyClient.pickingService
            .getPickingData(
                1,
                1,
                "A"
            )
            .callback
            .onSuccess { wrapper ->
                when (i) {
                    0 -> {
                        resultList = wrapper.data?.todos!!
                    }
                    1 -> {
                        resultList = wrapper.data?.todos?.filter {
                            it.status == "READY"
                        }!!.toMutableList()
                    }
                    2 -> {
                        resultList = wrapper.data?.todos?.filter {
                            it.status == "FINISH"
                        }!!.toMutableList()
                    }
                    3 -> {
                        resultList = wrapper.data?.todos?.filter {
                            it.status == "FINISH"
                        }!!.toMutableList().filter {
                            it.workerId == myWorkerId
                        }.toMutableList()
                    }
                }
                pickingAdapter.pickingList.addAll((resultList))
                pickingAdapter.notifyDataSetChanged()
            }
            .enqueue()
    }

    private fun initClickListener() {
        binding.ivPickingNotice.setOnClickListener {
            startActivity(Intent(this@PickingActivity, NoticeActivity::class.java))
        }

        binding.ivPickingMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.tvPickingPart.setOnClickListener {
            finish()
        }
    }

    private fun initCategory() {
        with(binding) {
            ivPickingOngoing.isSelected = true

            tvPickingAll.setOnClickListener {
                ivPickingAll.isSelected = true
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = false
                initAdapter(0)
            }
            tvPickingOngoing.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = true
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = false
                initAdapter(1)
            }
            tvPickingComplete.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = true
                ivPickingCompleteMine.isSelected = false
                initAdapter(2)
            }
            tvPickingCompleteMine.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = true
                initAdapter(3)
            }
        }
    }

    private fun connectWebSocket() {

//        val url = "ws://172.30.1.36:8080/ws/websocket" // 소켓에 연결하는 엔드포인트가 /socket일때 다음과 같음
//        val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)
//
//        stompClient.lifecycle().subscribe { lifecycleEvent ->
//            when (lifecycleEvent.type) {
//                LifecycleEvent.Type.OPENED -> {
//                    Log.d("OPEND", "!!")
//                }
//                LifecycleEvent.Type.CLOSED -> {
//                    Log.d("CLOSED", "!!")
//                }
//                LifecycleEvent.Type.ERROR -> {
//                    Log.d("ERROR", "!!")
//                    Log.d("CONNECT ERROR", lifecycleEvent.exception.toString())
//                }
//                else -> {
//                    Log.d("ELSE", lifecycleEvent.message)
//                }
//            }
//        }
//
//        val headerList = arrayListOf<StompHeader>()
//        headerList.add(StompHeader("worker-id", "1"))
//        stompClient.connect(headerList)
//        // stompClient.connect()
//        stompClient.send("/pub/pick/todos/1/A").subscribe()
//
//        stompClient.topic("/sub/pick/todos/1/A")
//            .subscribe { topicMessage ->
//                Log.d("message Receive", topicMessage.toString())
//            }
//
// //        stompClient.topic("/pub/pick/todos/1/A")
// //            .subscribe(
// //                { topicMessage -> Log.d("message Receive", topicMessage.toString()) }, { throwable -> "a" }
// //            )
//
//        Log.d("message", headerList.toString())

//        val data = JSONObject()
//        data.put("positionType", "1")
//        data.put("content", "test")
//        data.put("messageType", "CHAT")
//        data.put("destRoomCode", "test0912")

//        stompClient.send("/pub/pick/todos/1/A").subscribe()
//        stompClient.send("/pub/pick/todos/1/A", "").subscribe({  }, { throwable -> "a" })
    }

    override fun onClick(value: String) {
    }

    override fun onListClick(value: ArrayList<String>) {
        if (value[3] == "code") {
            val intent = Intent(this, PickingBarCodeActivity::class.java)
            intent.putExtra("pickingInfo", value)
            intent.putExtra("pickingPart", pickingPart)
            startActivity(intent)
        } else {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("isSuccess", true)
            intent.putExtra("partAddress", pickingPart + " " + value[0])
            intent.putExtra("item", value[1] + " " + value[2])
            startActivity(intent)
        }
    }
}
