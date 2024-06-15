package com.example.project_akhir_pab.ui.bluprint

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bluprint(
    val lokasiLahan: String,
    val statusKepemilikan: String,
    val penggunaanLahan: String,
    val luasLahan: String
): Parcelable