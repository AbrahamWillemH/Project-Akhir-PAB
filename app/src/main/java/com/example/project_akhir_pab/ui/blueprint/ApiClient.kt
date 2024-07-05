package com.example.project_akhir_pab.ui.blueprint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://6686bdb983c983911b03755e.mockapi.io"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val jaringanApiService: JaringanApiService by lazy {
        retrofit.create(JaringanApiService::class.java)
    }
    val networkApiService: NetworkApiService by lazy {
        retrofit.create(NetworkApiService::class.java)
    }
}
