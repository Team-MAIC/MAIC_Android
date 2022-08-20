package com.maic.kurlyhack.feature.picking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.local.PickingData
import com.maic.kurlyhack.databinding.ItemPickingBinding

class PickingAdapter : RecyclerView.Adapter<PickingAdapter.PickingViewHolder>() {
    val pickingList = mutableListOf<PickingData>()

    class PickingViewHolder(private val binding: ItemPickingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PickingData) {
            binding.tvPickingAddress.text = data.address
            binding.tvPickingName.text = data.name
            binding.tvPickingCount.text = data.count
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickingViewHolder {
        val binding = ItemPickingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PickingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PickingViewHolder, position: Int) {
        holder.onBind(pickingList[position])
    }

    override fun getItemCount(): Int {
        return pickingList.size
    }
}
