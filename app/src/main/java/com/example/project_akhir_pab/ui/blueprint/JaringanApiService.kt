package com.example.project_akhir_pab.ui.blueprint

import retrofit2.Call
import retrofit2.http.GET

interface JaringanApiService {
    @GET("/jaringan")
    fun getJaringan(): Call<List<JaringanResponse>>
}