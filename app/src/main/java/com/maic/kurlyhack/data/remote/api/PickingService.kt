package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.response.ResponsePickingData
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PickingService {
    @GET("pick/todos")
    fun getPickingData(
        @Header("worker-id") worker_id: Int,
        @Query("roundId") roundId: Int,
        @Query("area") area: String
    ): Call<ResponseWrapper<ResponsePickingData>>
}