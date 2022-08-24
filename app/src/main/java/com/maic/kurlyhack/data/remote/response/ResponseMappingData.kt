package com.maic.kurlyhack.data.remote.response

data class ResponseMappingData(
    val idx: IndexData,
    val todo: TodoData
)

data class IndexData(
    val clientIdx: Int,
    val basketNum: Int
)

data class TodoData(
    val roundId: Int,
    val productId: Int,
    val productName: String,
    val productAmount: Int,
    val currentAmount: Int,
    val color: String,
    val status: String
)
