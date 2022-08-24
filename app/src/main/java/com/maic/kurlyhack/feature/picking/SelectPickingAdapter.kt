package com.maic.kurlyhack.feature.picking

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.remote.response.RoundData
import com.maic.kurlyhack.databinding.ItemPartBinding
import com.maic.kurlyhack.feature.OnItemClick

class SelectPickingAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<SelectPickingAdapter.SelectPickingViewHolder>() {
    val partList = mutableListOf<RoundData>()

    class SelectPickingViewHolder(val binding: ItemPartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RoundData) {
            binding.tvPart.text = data.centerRoundNumber.toString() + "회차"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectPickingViewHolder {
        val binding = ItemPartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectPickingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectPickingViewHolder, position: Int) {
        holder.onBind(partList[position])

        var roundId = partList[position].roundId
        val infoList = ArrayList<String>()

        if (position == 0) {
            holder.itemView.setOnClickListener {
                holder.binding.tvPart.textSize = 19f
                holder.binding.tvPart.setTypeface(null, Typeface.BOLD)
                infoList.add(roundId.toString())
                infoList.add(holder.binding.tvPart.text.toString())
                onItemClick.onListClick(infoList)
            }
        } else {
            holder.binding.tvPart.setTextColor(Color.LTGRAY)
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }
}
