package com.example.mywallet.ui.accounts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    val searchQueries = MutableLiveData<List<String>>(mutableListOf())
    private val accounts = MutableLiveData<List<Account>>()

    init {
        accounts.value = listOf(
            Account("Mercado Pago", 100.0, "ARS"),
            Account("Banco Galicia", 200.0, "ARS"),
            Account("Efectivo", 300.0, "ARS"),
        )
    }

    fun addQuery(query: String) {
        val currentList = searchQueries.value?.toMutableList() ?: mutableListOf()
        //TODO: delete this log
        Log.i("AccountViewModel", "Adding query: $query")
        currentList.add(query)
        searchQueries.value = currentList
    }

    fun getAccounts(): LiveData<List<Account>> {
        return accounts
    }

    fun addAccount(account: Account) {
        val currentList = accounts.value?.toMutableList() ?: mutableListOf()
        currentList.add(account)
        accounts.value = currentList
    }
}