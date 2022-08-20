package com.maic.kurlyhack.feature.picking

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.local.PartData
import com.maic.kurlyhack.databinding.ItemPartBinding

class SelectPickingAdapter : RecyclerView.Adapter<SelectPickingAdapter.SelectPickingViewHolder>() {
    val partList = mutableListOf<PartData>()

    class SelectPickingViewHolder(private val binding: ItemPartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PartData) {
            binding.tvPart.text = data.part
            if (data.active) {
                binding.tvPart.textSize = 19f
                binding.tvPart.setTypeface(null, Typeface.BOLD)
            } else {
                binding.tvPart.setTextColor(Color.LTGRAY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectPickingViewHolder {
        val binding = ItemPartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectPickingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectPickingViewHolder, position: Int) {
        holder.onBind(partList[position])
    }

    override fun getItemCount(): Int {
        return partList.size
    }
}
