package com.example.project_akhir_pab.ui.prasarana

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prasarana2(
    val jenisPrasarana: String,
    val sumberDana: String,
    val rencanaInvestasi: String,
    val investasiTahap3: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable


