package com.maic.kurlyhack.data.remote.response

data class ResponseBoxData(
    val roundId: Int,
    val baskets: ArrayList<BasketsData>
)

data class BasketsData(
    val basketNum: Int,
    val orderInfoId: Int
)
