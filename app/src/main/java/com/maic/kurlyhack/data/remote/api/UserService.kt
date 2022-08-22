package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.request.RequestDeviceToken
import com.maic.kurlyhack.data.remote.response.ResponseBase
import com.maic.kurlyhack.data.remote.response.ResponseWorkerData
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface UserService {
    @GET("workers")
    fun getWorkerId(
        @Header("worker-id") worker_id: Int
    ): Call<ResponseWrapper<ResponseWorkerData>>

    @PUT("workers/device-token")
    fun putDeviceToken(
        @Header("worker-id") worker_id: Int,
        @Body body: RequestDeviceToken
    ): Call<ResponseWrapper<ResponseBase>>
}