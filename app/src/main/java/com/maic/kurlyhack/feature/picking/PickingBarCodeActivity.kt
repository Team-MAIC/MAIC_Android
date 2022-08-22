package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.maic.kurlyhack.R
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.databinding.ActivityPickingBarcodeBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class PickingBarCodeActivity : AppCompatActivity(), OnItemClick {
    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var capture: CaptureManager
    private lateinit var binding: ActivityPickingBarcodeBinding
    private var mPartAddress = ""
    private var mItem = ""
    private var productId = 0
    private var workerId = 0
    private var pickTodoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingBarcodeBinding.inflate(layoutInflater)

        getData()
        initDialog()
        initBtnClickListener()

        setContentView(binding.root)

        barcodeScannerView = findViewById(R.id.picking_scanner)

        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(this.intent, savedInstanceState)
        capture.decode()
        barcodeScannerView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                readBarcode(result.toString())
                moveActivity(result.toString())
            }
            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {
            }
        })
    }

    private fun getData() {
        val infoList = intent.getStringArrayListExtra("pickingInfo")
        val part = intent.getStringExtra("pickingPart")

        if (infoList != null) {
            val name = infoList[1].toString()
            val count = infoList[2].toString()
            val address = infoList[0].toString()
            productId = infoList[4].toInt()
            pickTodoId = infoList[5].toInt()
            mPartAddress = "$part $address"
            mItem = "$name $count"
            binding.tvPickingBarcodePart.text = mPartAddress
            binding.tvPickingBarcodeName.text = mItem
        }
    }

    private fun initDialog() {
        binding.btnPickingBarcodeStart.setOnClickListener {
            val dialog = BarcodeDialog(this)
            dialog.showDialog(this)
        }
    }

    private fun initBtnClickListener() {
        binding.ivPickingBarcodeBack.setOnClickListener {
            finish()
        }

        binding.ivPickingBarcodeMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }

    private fun moveActivity(code: String) {
        // TODO: 성공 실패 여부 - 받은 거랑 리스트 클릭 내용이랑 비교 / PickingActiivty로 돌아가거나 ItemActivity(오류)로 이동
        Log.d("###55", code)
        Log.d("###5", productId.toString())
        KurlyClient.barcodeService.getBarcodeData(
            productId.toString(),
            code
        ).callback.onSuccess {
            if (it.code != 1) {
                Toast.makeText(this, "바코드가 존재하지 않는 상품입니다.", Toast.LENGTH_SHORT).show()
            } else {
                if (it.data?.result == 1) {
                    // 성공
                    finish()
                    KurlyClient.barcodeService.putPickData(
                        workerId,
                        pickTodoId
                    ).callback.onSuccess {
                        Log.d("##", "pickdata 변경")
                    }.enqueue()
                } else {
                    // 실패
                    finish()
                    val intent = Intent(this, ItemActivity::class.java)
                    intent.putExtra("isSuccess", false)
                    intent.putExtra("codeItem", it.data?.compare?.productName)
                    intent.putExtra("partAddress", mPartAddress)
                    intent.putExtra("item", mItem)
                    startActivity(intent)
                }
            }
        }.enqueue()
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    fun readBarcode(barcode: String) {
        Toast.makeText(this, barcode, Toast.LENGTH_LONG).show()
    }

    override fun onClick(value: String) {
        moveActivity(value)
    }

    override fun onListClick(value: ArrayList<String>) {
    }
}
