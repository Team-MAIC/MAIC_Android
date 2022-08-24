package com.maic.kurlyhack.data.remote.request

data class RequestMessage(
    val roundId: Int,
    val productId: Int,
    val productName: String,
    val centerRoundNumber: Int,
    val amount: Int
)
