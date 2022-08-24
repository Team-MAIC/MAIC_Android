package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.request.RequestDasSubscribe
import com.maic.kurlyhack.data.remote.request.RequestMapping
import com.maic.kurlyhack.data.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface DasService {
    @GET("das/todos/refresh")
    fun getBoxData(
        @Query("centerId") centerId: Int,
        @Query("passage") passage: Int
    ): Call<ResponseWrapper<ResponseBoxData>>

    @POST("das/todos/subscribe")
    fun postDasSubscribe(
        @Body body: RequestDasSubscribe
    ): Call<ResponseWrapper<ResponseBase>>

    @GET("das/todos/{roundId}")
    fun getDasData(
        @Path("roundId") roundId: Int
    ): Call<ResponseWrapper<ResponseDasData>>

    @POST("das/todos/baskets/mapping/{centerId}/{passage}")
    fun postMapping(
        @Path("centerId") centerId: Int,
        @Path("passage") passage: Int,
        @Body body: RequestMapping
    ): Call<ResponseWrapper<ResponseBase>>
}