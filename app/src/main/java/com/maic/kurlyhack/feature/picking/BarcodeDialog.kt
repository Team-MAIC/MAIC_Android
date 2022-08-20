package com.maic.kurlyhack.feature.picking

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.maic.kurlyhack.R
import com.maic.kurlyhack.feature.OnItemClick

class BarcodeDialog(context: Context) {
    private val dialog = Dialog(context)

    fun showDialog(onItemClick: OnItemClick) {
        dialog.setContentView(R.layout.dialog_barcode)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val mBtn = dialog.findViewById<Button>(R.id.btn_dialog_confirm)
        val mEt = dialog.findViewById<EditText>(R.id.et_dialog_input)

        mEt.addTextChangedListener {
            mBtn.isEnabled = mEt.text.toString().isNotEmpty()
        }

        mBtn.setOnClickListener {
            onItemClick.onClick(mEt.text.toString())
            dialog.dismiss()
        }
    }
}
