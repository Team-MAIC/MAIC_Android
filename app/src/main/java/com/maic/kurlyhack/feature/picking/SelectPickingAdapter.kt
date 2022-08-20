package com.maic.kurlyhack.feature.picking

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.local.PartData
import com.maic.kurlyhack.databinding.ItemPartBinding
import com.maic.kurlyhack.feature.OnItemClick

class SelectPickingAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<SelectPickingAdapter.SelectPickingViewHolder>() {
    val partList = mutableListOf<PartData>()

    class SelectPickingViewHolder(val binding: ItemPartBinding) : RecyclerView.ViewHolder(binding.root) {
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

        if (holder.binding.tvPart.currentTextColor != Color.LTGRAY) {
            holder.itemView.setOnClickListener {
                onItemClick.onClick(holder.binding.tvPart.text.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }
}
