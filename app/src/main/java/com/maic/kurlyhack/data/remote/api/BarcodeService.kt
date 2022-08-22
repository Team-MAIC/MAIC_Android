package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.response.ResponseBarcodeData
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface BarcodeService {
    @GET("products/valid/{productId}/{barcode}")
    fun getBarcodeData(
        @Path("productId") productId: String,
        @Path("barcode") barcode: String
    ): Call<ResponseWrapper<ResponseBarcodeData>>

    @PUT("pick/todos/{pickTodoId}")
    fun putPickData(
        @Header("worker-id") workerId: Int,
        @Path("pickTodoId") pickTodoId: Int
    ): Call<ResponseWrapper<ResponseBarcodeData>>
}