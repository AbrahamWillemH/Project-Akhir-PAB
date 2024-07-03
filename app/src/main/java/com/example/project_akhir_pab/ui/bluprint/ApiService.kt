package com.example.project_akhir_pab.ui.bluprint

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("bluprints")
    fun getBluprints(): Call<List<Bluprint>>

    @GET("bluprints/{id}")
    fun getBluprintById(@Path("id") id: Int): Call<Bluprint>
}