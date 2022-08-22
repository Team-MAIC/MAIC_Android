package com.maic.kurlyhack.data.remote.response

data class ResponseWrapper<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)
