package com.maic.kurlyhack.feature.das

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.R
import com.maic.kurlyhack.data.remote.response.BasketItemData
import com.maic.kurlyhack.databinding.ItemDasBinding

class DasAdapter : RecyclerView.Adapter<DasAdapter.DasViewHolder>() {
    val dasList = mutableListOf<BasketItemData>()

    class DasViewHolder(val binding: ItemDasBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BasketItemData) {
            with(binding) {
                tvDasBox.text = "BOX" + data.basketNum
                if (data.todo == null) {
                    ivDasColor.setImageResource(R.drawable.oval_fill_white)
                } else {
                    when (data.todo.color) {
                        "RED" -> ivDasColor.setImageResource(R.drawable.oval_fill_red)
                        "YELLOW" -> ivDasColor.setImageResource(R.drawable.oval_fill_yellow)
                        "GREEN" -> ivDasColor.setImageResource(R.drawable.oval_fill_green)
                        "BLUE" -> ivDasColor.setImageResource(R.drawable.oval_fill_blue)
                    }
                    if (data.todo.status == "WRONG") {
                        ivDasColor.setImageResource(R.drawable.oval_fill_black)
                        ivDasColor.tag = Integer.valueOf(R.drawable.oval_fill_black)
                    }
                    tvDasName.text = data.todo.productName
                    tvDasCount.text = data.todo.productAmount.toString() + "개"
                    tvDasCountCurrent.text = "(현재 " + data.todo.currentAmount.toString() + "개)"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DasViewHolder {
        val binding = ItemDasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DasViewHolder, position: Int) {
        holder.onBind(dasList[position])

        holder.itemView.setOnClickListener {
            if (holder.binding.ivDasColor.tag == R.drawable.oval_fill_black) {
                val intent = Intent(it.context, CountErrorActivity::class.java)
                intent.putExtra("addressBox", dasList[position].basketNum)
                intent.putExtra("Item", dasList[position].todo.productName)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return dasList.size
    }
}
