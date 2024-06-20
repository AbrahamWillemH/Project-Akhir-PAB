package com.example.project_akhir_pab.ui.lahan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lahan(
    val lokasiLahan: String,
    val statusKepemilikan: String,
    val penggunaanLahan: String,
    val luasLahan: String
) : Parcelable
