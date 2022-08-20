package com.maic.kurlyhack.feature.das

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.R
import com.maic.kurlyhack.data.local.DasData
import com.maic.kurlyhack.databinding.ItemDasBinding

class DasAdapter : RecyclerView.Adapter<DasAdapter.DasViewHolder>() {
    val dasList = mutableListOf<DasData>()

    class DasViewHolder(val binding: ItemDasBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: DasData) {
            with(binding) {
                when (data.color) {
                    "빨강" -> ivDasColor.setImageResource(R.drawable.oval_fill_red)
                    "노랑" -> ivDasColor.setImageResource(R.drawable.oval_fill_yellow)
                    "초록" -> ivDasColor.setImageResource(R.drawable.oval_fill_green)
                    "파랑" -> ivDasColor.setImageResource(R.drawable.oval_fill_blue)
                    "검정" -> {
                        ivDasColor.setImageResource(R.drawable.oval_fill_black)
                        ivDasColor.tag = Integer.valueOf(R.drawable.oval_fill_black)
                    }
                    else -> ivDasColor.setImageResource(R.drawable.oval_fill_white)
                }
                tvDasBox.text = data.box
                tvDasName.text = data.name
                tvDasWeight.text = data.weight
                tvDasCount.text = data.count
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
                intent.putExtra("addressBox", dasList[position].box)
                intent.putExtra("Item", dasList[position].name)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return dasList.size
    }
}
