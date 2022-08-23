package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.request.RequestDasSubscribe
import com.maic.kurlyhack.data.remote.response.ResponseBase
import com.maic.kurlyhack.data.remote.response.ResponseBoxData
import com.maic.kurlyhack.data.remote.response.ResponseDasData
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.*

interface DasService {
    @POST("das/todos/subscribe")
    fun postDasSubscribe(
        @Body body: RequestDasSubscribe
    ): Call<ResponseWrapper<ResponseBase>>
}