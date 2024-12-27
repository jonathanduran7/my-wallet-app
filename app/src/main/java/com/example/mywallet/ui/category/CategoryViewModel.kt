package com.example.mywallet.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>(
        listOf(
            Category("Compras"),
            Category("Auto"),
            Category("Otros"),
        )
    )
    val categories: LiveData<List<Category>> get() = _categories



    fun addCategory(category: Category) {
        val currentList = categories.value?.toMutableList() ?: mutableListOf()
        currentList.add(category)
        _categories.value = currentList
    }
}