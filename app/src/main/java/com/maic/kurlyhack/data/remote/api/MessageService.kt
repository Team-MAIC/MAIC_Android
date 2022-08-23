package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.request.RequestMessage
import com.maic.kurlyhack.data.remote.response.ResponseBase
import com.maic.kurlyhack.data.remote.response.ResponseNotice
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.*

interface MessageService {
    @POST("messages")
    fun postMessages(
        @Header("worker-id") worker_id: Int,
        @Body body: RequestMessage
    ): Call<ResponseWrapper<ResponseBase>>

    @GET("messages")
    fun getMessage(
        @Header("worker-id") worker_id: Int,
    ): Call<ResponseWrapper<ResponseNotice>>

    @PUT("messages/{messageId}")
    fun putConfirmMessage(
        @Path("messageId") messageId: Int
    ): Call<ResponseWrapper<ResponseBase>>
}