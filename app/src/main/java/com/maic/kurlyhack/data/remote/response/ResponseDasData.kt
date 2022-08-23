package com.maic.kurlyhack.data.remote.response

data class ResponseDasData(
    val colors: ArrayList<ColorData>,
    val baskets: ArrayList<BasketItemData>
)

data class ColorData(
    val color: String,
    val productName: String
)

data class BasketItemData(
    val basketNum: Int,
    val todo: DasTodoData
)

data class DasTodoData(
    val roundId: Int,
    val productId: Int,
    val productName: String,
    val productAmount: Int,
    val currentAmount: Int,
    val color: String,
    val status: String
)
