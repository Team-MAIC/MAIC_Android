package com.maic.kurlyhack.data.remote.response

data class ResponsePickingData(
    val todos: ArrayList<PickingTodoData>
)

data class PickingTodoData(
    val pickTodoId: Int,
    val productId: Int,
    val productName: String,
    val productThumbnail: String,
    val area: String,
    val line: Int,
    val location: Int,
    val amount: Int,
    val status: String,
    val workerId: Int
)
