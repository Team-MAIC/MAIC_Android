package com.maic.kurlyhack.data.remote

import com.maic.kurlyhack.data.remote.api.PickingService
import com.maic.kurlyhack.data.remote.api.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KurlyClient {
    private const val BASE_URL = "http://172.30.1.36:8080/"

    val userService: UserService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(UserService::class.java)
    }

    val pickingService: PickingService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(PickingService::class.java)
    }

    private fun <T> provideService(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpLoggingClient())
        .build()
        .create(clazz)

    private fun provideHttpLoggingClient(): OkHttpClient =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder().addInterceptor(it).build()
        }
}
