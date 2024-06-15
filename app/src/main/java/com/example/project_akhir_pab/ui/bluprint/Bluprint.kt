package com.example.project_akhir_pab.ui.bluprint

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bluprint(
    val name: String,
    val inst: String,
    val desc: String,
    val img: Int
): Parcelable