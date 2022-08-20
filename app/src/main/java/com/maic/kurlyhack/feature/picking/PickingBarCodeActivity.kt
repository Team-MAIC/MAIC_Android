package com.maic.kurlyhack.feature.picking

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
import com.maic.kurlyhack.databinding.ActivityPickingBarcodeBinding

class PickingBarCodeActivity : AppCompatActivity() {
    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var capture: CaptureManager
    private lateinit var binding: ActivityPickingBarcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingBarcodeBinding.inflate(layoutInflater)

        getData()
        initDialog()

        setContentView(binding.root)

        barcodeScannerView = findViewById(R.id.picking_scanner)

        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(this.intent, savedInstanceState)
        capture.decode()
        barcodeScannerView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                readBarcode(result.toString())
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
            binding.tvPickingBarcodePart.text = "$part $address"
            binding.tvPickingBarcodeName.text = "$name $count"
        }
    }

    private fun initDialog() {
        binding.btnPickingBarcodeStart.setOnClickListener {
            val dialog = BarcodeDialog(this)
            dialog.showDialog()
        }
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
}
