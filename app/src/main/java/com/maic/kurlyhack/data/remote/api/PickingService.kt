package com.maic.kurlyhack.data.remote.api

import com.maic.kurlyhack.data.remote.request.RequestSubscribe
import com.maic.kurlyhack.data.remote.response.ResponseBase
import com.maic.kurlyhack.data.remote.response.ResponsePickingData
import com.maic.kurlyhack.data.remote.response.ResponseRoundData
import com.maic.kurlyhack.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.*

interface PickingService {
    @GET("rounds")
    fun getRoundsData(
        @Header("worker-id") worker_id: Int
    ): Call<ResponseWrapper<ResponseRoundData>>
}
