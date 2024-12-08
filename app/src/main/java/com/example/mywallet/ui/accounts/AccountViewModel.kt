package com.example.mywallet.ui.accounts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    val searchQueries = MutableLiveData<List<String>>(mutableListOf())

    fun addQuery(query: String) {
        val currentList = searchQueries.value?.toMutableList() ?: mutableListOf()
        Log.i("AccountViewModel", "Adding query: $query")
        currentList.add(query)
        searchQueries.value = currentList
    }
}