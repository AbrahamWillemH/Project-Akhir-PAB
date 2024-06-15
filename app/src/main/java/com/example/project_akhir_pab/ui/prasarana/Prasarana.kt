package com.example.project_akhir_pab.ui.prasarana

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Prasarana.java
@Parcelize
data class Prasarana(
    val nama: String,
    val jenis: String,
    val deskripsi: String,
    val jumlah: Int
) : Parcelable