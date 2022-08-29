package com.maic.kurlyhack.feature.picking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.request.RequestSubscribe
import com.maic.kurlyhack.data.remote.response.PickingTodoData
import com.maic.kurlyhack.databinding.ActivityPickingBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.feature.help.PickingHelp1Activity
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.util.*

class PickingActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityPickingBinding
    private lateinit var pickingAdapter: PickingAdapter
    private var area = ""
    private var workerId = 0
    private var mCategory = 10
    private var roundId = 0
    private var centerRoundNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingBinding.inflate(layoutInflater)

        getData()
        initAdapter(1)
        mCategory = 1
        initClickListener()
        initCategory()
        connectWebSocket()
        checkNotice()

        setContentView(binding.root)
    }

    private fun getData() {
        workerId = intent.getIntExtra("workerId", 0)
        roundId = intent.getIntExtra("roundId", 0)
        area = intent.getStringExtra("area").toString()
        centerRoundNumber = intent.getStringExtra("centerRoundNumber")!![0].toString()
        binding.tvPickingPart.text = centerRoundNumber + "회차"
    }

    private fun initAdapter(i: Int) {
        pickingAdapter = PickingAdapter(this)
        binding.rvPicking.adapter = pickingAdapter
        var resultList = mutableListOf<PickingTodoData>()

        KurlyClient.pickingService
            .getPickingData(
                workerId,
                roundId,
                area
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
                            it.workerId == workerId
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
            val intent = Intent(this@PickingActivity, NoticeActivity::class.java)
            intent.putExtra("workerId", workerId)
            startActivity(intent)
        }

        binding.ivPickingMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.tvPickingPart.setOnClickListener {
            finish()
        }

        binding.ivCountPickingHelp.setOnClickListener {
            startActivity(Intent(this, PickingHelp1Activity::class.java))
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
                mCategory = 0
                initAdapter(mCategory)
            }
            tvPickingOngoing.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = true
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = false
                mCategory = 1
                initAdapter(mCategory)
            }
            tvPickingComplete.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = true
                ivPickingCompleteMine.isSelected = false
                mCategory = 2
                initAdapter(mCategory)
            }
            tvPickingCompleteMine.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = true
                mCategory = 3
                initAdapter(mCategory)
            }
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

        val requestSubscribe = RequestSubscribe(
            roundId = roundId,
            area = area
        )

        KurlyClient.pickingService.postSubscribe(
            requestSubscribe
        ).callback.onSuccess {
            Log.d("###", "post완료")
        }.enqueue()

        stompClient.topic("/sub/pick/todos/$roundId/$area")
            .subscribe {
                Log.d("message Receive", it.payload)
                runOnUiThread {
                    initAdapter(mCategory)
                }
            }
    }

    private fun checkNotice() {
        Log.d("###", "a")
        KurlyClient.messageService.getMessage(
            workerId
        ).callback.onSuccess {
            Log.d("###", it.data.toString())
            if (it.data!!.messages.size == 0) {
                Log.d("###", "b")
                binding.ivPickingNoticeYes.visibility = View.INVISIBLE
            } else {
                Log.d("###", "c")
                binding.ivPickingNoticeYes.visibility = View.VISIBLE
            }
        }.enqueue()
    }

    override fun onClick(value: String) {
    }

    override fun onListClick(value: ArrayList<String>) {
        if (value[3] == "code") {
            val intent = Intent(this, PickingBarCodeActivity::class.java)
            intent.putExtra("workerId", workerId)
            intent.putExtra("pickingInfo", value)
            intent.putExtra("pickingPart", centerRoundNumber + "회차")
            startActivity(intent)
        } else {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("isSuccess", true)
            intent.putExtra("partAddress", centerRoundNumber + "회차 " + value[0])
            intent.putExtra("item", value[1] + " " + value[2])
            intent.putExtra("picture", value[4])
            startActivity(intent)
        }
    }
}
