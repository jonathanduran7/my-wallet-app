package com.example.mywallet.ui.movements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovementViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is movement Fragment"
    }
    val text: LiveData<String> = _text
}