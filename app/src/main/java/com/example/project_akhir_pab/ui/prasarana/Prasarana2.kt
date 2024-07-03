package com.example.project_akhir_pab.ui.prasarana

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prasarana2(
    val name : String,
    val name2 : String,
    val desc : String,
    val img : Int,
) : Parcelable {
}