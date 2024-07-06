package com.example.project_akhir_pab.ui.blueprint

import retrofit2.Call
import retrofit2.http.GET

interface NetworkApiService {
    @GET("/network")
    fun getNetwork(): Call<List<NetworkResponse>>
}