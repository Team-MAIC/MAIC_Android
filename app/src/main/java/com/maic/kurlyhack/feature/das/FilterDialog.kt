package com.maic.kurlyhack.feature.das

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.RadioGroup
import com.maic.kurlyhack.R
import com.maic.kurlyhack.feature.OnItemClick

class FilterDialog(context: Context) {
    private val dialog = Dialog(context)

    fun showDialog(onItemClick: OnItemClick) {
        dialog.setContentView(R.layout.dialog_filter)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val mBtn = dialog.findViewById<Button>(R.id.btn_filter_confirm)
        val mGroup = dialog.findViewById<RadioGroup>(R.id.rg_filter)
        var mFilter = ""

        mGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.rb_filter_all -> {
                    mFilter = "ALL"
                    mBtn.isEnabled = true
                }
                R.id.rb_filter_ongoing -> {
                    mFilter = "ONGOING"
                    mBtn.isEnabled = true
                }
                R.id.rb_filter_red -> {
                    mFilter = "RED"
                    mBtn.isEnabled = true
                }
                R.id.rb_filter_yellow -> {
                    mFilter = "YELLOW"
                    mBtn.isEnabled = true
                }
                R.id.rb_filter_green -> {
                    mFilter = "GREEN"
                    mBtn.isEnabled = true
                }
                R.id.rb_filter_blue -> {
                    mFilter = "BLUE"
                    mBtn.isEnabled = true
                }
                R.id.rb_filter_black -> {
                    mFilter = "BLACK"
                    mBtn.isEnabled = true
                }
            }
        }

        mBtn.setOnClickListener {
            onItemClick.onClick(mFilter)
            dialog.dismiss()
        }
    }
}
