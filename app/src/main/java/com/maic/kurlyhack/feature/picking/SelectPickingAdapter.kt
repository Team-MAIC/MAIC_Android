package com.maic.kurlyhack.feature.picking

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.remote.response.RoundData
import com.maic.kurlyhack.databinding.ItemPartBinding
import com.maic.kurlyhack.feature.OnItemClick

class SelectPickingAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<SelectPickingAdapter.SelectPickingViewHolder>() {
    val partList = mutableListOf<RoundData>()

    class SelectPickingViewHolder(val binding: ItemPartBinding) : RecyclerView.ViewHolder(binding.root) {
        private var isFirst = true
        fun onBind(data: RoundData) {
            binding.tvPart.text = data.roundId.toString() + "회차"
            if (isFirst) {
                binding.tvPart.textSize = 19f
                binding.tvPart.setTypeface(null, Typeface.BOLD)
                isFirst = false
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

        var roundId = partList[position].roundId
        if (holder.binding.tvPart.currentTextColor != Color.LTGRAY) {
            holder.itemView.setOnClickListener {
                val partList = ArrayList<String>()
                partList.add(holder.binding.tvPart.text.toString())
                partList.add(roundId.toString())
                onItemClick.onListClick(partList)
            }
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }
}
