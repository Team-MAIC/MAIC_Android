package com.maic.kurlyhack.feature.das

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import com.maic.kurlyhack.R
import com.maic.kurlyhack.feature.OnItemClick

class BarcodeErrorDialog(context: Context) {
    private val dialog = Dialog(context)

    fun showDialog(onItemClick: OnItemClick) {
        dialog.setContentView(R.layout.dialog_barcode_error)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val mBtn = dialog.findViewById<Button>(R.id.btn_barcode_error_confirm)

        mBtn.setOnClickListener {
            onItemClick.onClick("ok")
            dialog.dismiss()
        }
    }
}
