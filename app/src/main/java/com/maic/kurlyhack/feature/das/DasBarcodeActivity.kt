package com.maic.kurlyhack.feature.das

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
import com.maic.kurlyhack.databinding.ActivityDasBarcodeBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.feature.picking.BarcodeDialog

class DasBarcodeActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityDasBarcodeBinding
    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasBarcodeBinding.inflate(layoutInflater)

        initClickListener()

        setContentView(binding.root)

        barcodeScannerView = findViewById(R.id.picking_scanner)

        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(this.intent, savedInstanceState)
        capture.decode()
        barcodeScannerView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                readBarcode(result.toString())
                checkBarcode()
            }
            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {
            }
        })
    }

    private fun initClickListener() {
        binding.btnDasBarcode.setOnClickListener {
            val dialog = BarcodeDialog(this)
            dialog.showDialog(this)
        }
    }

    fun readBarcode(barcode: String) {
        Toast.makeText(this, barcode, Toast.LENGTH_LONG).show()
    }

    private fun checkBarcode() {
        // TODO: 성공 실패 여부 - 받은 거랑 리스트 클릭 내용이랑 비교 / 성공 api, 돌아가거나 오류 다이알로그로 이동
        // 실패
        val dialog = BarcodeErrorDialog(this)
        dialog.showDialog(this)

//        // 성공
//        // 성공 api
//        finish()
    }

    override fun onClick(value: String) {
        Log.d("####", value)
        if (value == "error") {
            finish()
        } else if (value.length > 10) {
            // TODO: 바코드 비교
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
