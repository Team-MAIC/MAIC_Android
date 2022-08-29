package com.maic.kurlyhack.feature.das

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.maic.kurlyhack.R
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.databinding.ActivityDasBarcodeBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.feature.help.DasHelp1Activity
import com.maic.kurlyhack.feature.help.DasHelp8Activity
import com.maic.kurlyhack.feature.picking.BarcodeDialog
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class DasBarcodeActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityDasBarcodeBinding
    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var capture: CaptureManager
    var roundId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasBarcodeBinding.inflate(layoutInflater)

        getData()
        initClickListener()

        setContentView(binding.root)

        barcodeScannerView = findViewById(R.id.picking_scanner)

        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(this.intent, savedInstanceState)
        capture.decode()
        barcodeScannerView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                checkBarcode(result.toString())
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {
            }
        })
    }

    private fun getData() {
    }

    private fun initClickListener() {
        binding.btnDasBarcode.setOnClickListener {
            val dialog = BarcodeDialog(this)
            dialog.showDialog(this)
        }

        binding.ivDasBarcodeBack.setOnClickListener {
            finish()
        }

        binding.ivDasBarcodeMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivDasBarcodeHelp.setOnClickListener {
            startActivity(Intent(this, DasHelp1Activity::class.java))
        }
    }

    private fun checkBarcode(code: String) {
        KurlyClient.barcodeService.getDasCodeData(
            code
        ).callback.onSuccess { it ->
            if (it.code == 3001) {
                Toast.makeText(this, "바코드가 존재하지 않는 상품입니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else if (it.code == 1) {
                KurlyClient.barcodeService.putDasData(
                    intent.getIntExtra("roundId", roundId),
                    it.data!!.productId
                ).callback.onSuccess {
                    if (it.code == 4002) {
                        val dialog = BarcodeErrorDialog(this)
                        dialog.showDialog(this)
                    } else {
                        Log.d("###", "다스 바코드 데이터 전송 완료")
                        finish()
                        val intent = Intent(this, DasActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                        startActivity(intent)
                    }
                }.enqueue()
            }
        }.enqueue()
    }

    override fun onClick(value: String) {
        Log.d("####", value)
        if (value == "ok") {
            finish()
        }
    }

    override fun onListClick(value: ArrayList<String>) {
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
}
