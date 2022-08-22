package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.response.ResponseWorkerData
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("workers")
    fun getWorkerId(
        @Header("worker-id") worker_id: Int
    ): Call<ResponseWrapper<ResponseWorkerData>>
}