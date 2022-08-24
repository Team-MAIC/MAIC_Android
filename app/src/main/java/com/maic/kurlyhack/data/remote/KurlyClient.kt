package com.maic.kurlyhack.data.remote

import com.maic.kurlyhack.data.remote.api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KurlyClient {
    private const val BASE_URL = "https://project-maic.com/"

    val userService: UserService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(UserService::class.java)
    }

    val pickingService: PickingService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(PickingService::class.java)
    }

    val barcodeService: BarcodeService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(BarcodeService::class.java)
    }

    val dasService: DasService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(DasService::class.java)
    }

    val messageService: MessageService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(MessageService::class.java)
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
