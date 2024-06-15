package com.l0122017.alvito.projectakhir.ui.lahan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LahanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Lahan Fragment"
    }
    val text: LiveData<String> = _text
}
