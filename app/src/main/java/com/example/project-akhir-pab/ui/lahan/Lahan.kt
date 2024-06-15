package com.l0122017.alvito.projectakhir.ui.lahan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lahan(
    val lokasiLahan: String,
    val statusKepemilikan: String,
    val penggunaanLahan: String,
    val luasLahan: String
) : Parcelable
