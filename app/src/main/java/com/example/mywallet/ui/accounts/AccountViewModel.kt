package com.example.mywallet.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AccountViewModel : ViewModel() {

    private val _searchQueries = MutableLiveData<List<String>>(emptyList())
    val searchQueries: LiveData<List<String>> get() = _searchQueries

    private val _accounts = MutableLiveData<List<Account>>(
        listOf(
            Account("Mercado Pago", 100.0, "ARS"),
            Account("Banco Galicia", 200.0, "ARS"),
            Account("Efectivo", 300.0, "ARS")
        )
    )
    val accounts: LiveData<List<Account>> get() = _accounts

    fun addQuery(query: String) {
        val updatedQueries = _searchQueries.value.orEmpty().toMutableList().apply {
            add(query)
        }
        _searchQueries.value = updatedQueries
    }

    fun addAccount(account: Account) {
        val updatedAccounts = _accounts.value.orEmpty().toMutableList().apply {
            add(account)
        }
        _accounts.value = updatedAccounts
    }
}
