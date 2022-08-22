package com.maic.kurlyhack.feature.picking

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.remote.response.PickingTodoData
import com.maic.kurlyhack.databinding.ItemPickingBinding
import com.maic.kurlyhack.feature.OnItemClick

class PickingAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<PickingAdapter.PickingViewHolder>() {
    val pickingList = mutableListOf<PickingTodoData>()

    class PickingViewHolder(val binding: ItemPickingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PickingTodoData) {
//            when(i) {
//                1 -> {
//                    binding.tvPickingAddress.text = data.area + data.line + "-" + data.location
//                    binding.tvPickingName.text = data.productName
//                    binding.tvPickingCount.text = data.amount.toString()
//
//                    if (data.status == "FINISH") {
//                        binding.tvPickingCount.setTextColor(Color.LTGRAY)
//                        binding.tvPickingName.setTextColor(Color.LTGRAY)
//                        binding.tvPickingAddress.setTextColor(Color.LTGRAY)
//                    }
//                }
//                2 -> {
//                    if (data.status == "READY") {
//                        binding.tvPickingAddress.text = data.area + data.line + "-" + data.location
//                        binding.tvPickingName.text = data.productName
//                        binding.tvPickingCount.text = data.amount.toString()
//                    }
//                }
//                3 -> {
//                    if(data.status == "FINISH") {
//                        binding.tvPickingAddress.text = data.area + data.line + "-" + data.location
//                        binding.tvPickingName.text = data.productName
//                        binding.tvPickingCount.text = data.amount.toString()
//                    }
//                }
//                4 -> {
//                    if(data.status == "FINISH") {
//
//                    }
//                }
//            }
            binding.tvPickingAddress.text = data.area + data.line + "-" + data.location
            binding.tvPickingName.text = data.productName
            binding.tvPickingCount.text = data.amount.toString()

            if (data.status == "FINISH") {
                binding.tvPickingCount.setTextColor(Color.LTGRAY)
                binding.tvPickingName.setTextColor(Color.LTGRAY)
                binding.tvPickingAddress.setTextColor(Color.LTGRAY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickingViewHolder {
        val binding = ItemPickingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PickingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PickingViewHolder, position: Int) {
        holder.onBind(pickingList[position])

        if (holder.binding.tvPickingAddress.currentTextColor != Color.LTGRAY) {
            val infoList = ArrayList<String>()
            infoList.add(holder.binding.tvPickingAddress.text.toString())
            infoList.add(holder.binding.tvPickingName.text.toString())
            infoList.add(holder.binding.tvPickingCount.text.toString())

            holder.binding.ivPickingBarcode.setOnClickListener {
                infoList.add(3, "code")
                onItemClick.onListClick(infoList)
            }
            holder.binding.tvPickingName.setOnClickListener {
                infoList.add(3, "list")
                onItemClick.onListClick(infoList)
            }
            holder.binding.tvPickingAddress.setOnClickListener {
                infoList.add(3, "list")
                onItemClick.onListClick(infoList)
            }
        }
    }

    override fun getItemCount(): Int {
        return pickingList.size
    }
}
