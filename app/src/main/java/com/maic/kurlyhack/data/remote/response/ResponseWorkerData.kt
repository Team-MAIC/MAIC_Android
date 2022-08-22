package com.maic.kurlyhack.data.remote.response

data class ResponseWorkerData(
    val workerId: Int,
    val centerId: Int,
    val centerName: String,
    val role: String,
    val passage: String,
    val area: String,
)
