package com.maic.kurlyhack.feature.das

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.R
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.response.BasketItemData
import com.maic.kurlyhack.databinding.ItemDasBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.callback

class DasAdapter(private val onItemClick: OnItemClick) :
    RecyclerView.Adapter<DasAdapter.DasViewHolder>() {
    val dasList = mutableListOf<BasketItemData>()

    class DasViewHolder(val binding: ItemDasBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BasketItemData) {
            with(binding) {
                tvDasBox.text = "BOX " + data.idx.basketNum
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
            val item = dasList[position].todo
            val infoList = ArrayList<String>()
            var image = ""

            if (holder.binding.ivDasColor.tag == R.drawable.oval_fill_black) {
                if (item.currentAmount < item.productAmount) {
                    val count = item.productAmount - item.currentAmount
                    infoList.add(dasList[position].idx.basketNum.toString())
                    infoList.add(item.productName)
                    infoList.add(count.toString())
                    infoList.add(item.productId.toString())
                    KurlyClient.dasService.getProductData(
                        item.productId
                    ).callback.onSuccess {
                        image = it.data!!.productThumbnail
                        infoList.add(image)
                        onItemClick.onListClick(infoList)
                    }.enqueue()
                } else {
                    var count = item.currentAmount - item.productAmount
                    Toast.makeText(
                        holder.itemView.context,
                        item.productName + " " + count + "개 초과 예상",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onBindViewHolder(
        @NonNull holder: DasViewHolder,
        position: Int,
        @NonNull payloads: MutableList<Any>
    ) {

        holder.onBind(dasList[position])
        if (payloads.isNotEmpty()) {
            if (payloads[0] == "finish") {
                holder.binding.ivDasColor.setImageResource(R.drawable.oval_fill_white)
                holder.binding.tvDasCountCurrent.text = ""
                holder.binding.tvDasCount.text = ""
                holder.binding.tvDasName.text = ""
            } else {
                var myPayLoads = payloads.toString()
                val count = myPayLoads.split(",")[0].substring(1)
                val pCount = myPayLoads.split(",")[1]
                val color = myPayLoads.split(",")[2]
                val status = myPayLoads.split(",")[3]
                val name = myPayLoads.split(",")[4].substring(0, myPayLoads.split(",")[4].length - 1)

                when (color) {
                    "RED" -> holder.binding.ivDasColor.setImageResource(R.drawable.oval_fill_red)
                    "YELLOW" -> holder.binding.ivDasColor.setImageResource(R.drawable.oval_fill_yellow)
                    "GREEN" -> holder.binding.ivDasColor.setImageResource(R.drawable.oval_fill_green)
                    "BLUE" -> holder.binding.ivDasColor.setImageResource(R.drawable.oval_fill_blue)
                }
                if (status == "WRONG") {
                    holder.binding.ivDasColor.setImageResource(R.drawable.oval_fill_black)
                    holder.binding.ivDasColor.tag = Integer.valueOf(R.drawable.oval_fill_black)
                }
                holder.binding.tvDasCountCurrent.text = "(현재 " + count + "개)"
                holder.binding.tvDasName.text = name
                holder.binding.tvDasCount.text = pCount + "개"

                holder.itemView.setOnClickListener {
                    val item = dasList[position].todo
                    val infoList = ArrayList<String>()
                    var image = ""

                    if (holder.binding.ivDasColor.tag == R.drawable.oval_fill_black) {
                        if (count.toInt() < item.productAmount) {
                            val count = item.productAmount - count.toInt()
                            infoList.add(dasList[position].idx.basketNum.toString())
                            infoList.add(item.productName)
                            infoList.add(count.toString())
                            infoList.add(item.productId.toString())
                            KurlyClient.dasService.getProductData(
                                item.productId
                            ).callback.onSuccess {
                                image = it.data!!.productThumbnail
                                infoList.add(image)
                                onItemClick.onListClick(infoList)
                            }.enqueue()
                        } else {
                            var count = count.toInt() - item.productAmount
                            Toast.makeText(
                                holder.itemView.context,
                                item.productName + " " + count + "개 초과 예상",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return dasList.size
    }
}
