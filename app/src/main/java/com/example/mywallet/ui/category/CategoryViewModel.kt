package com.example.mywallet.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {

    private val categories = MutableLiveData<List<Category>>()

    init {
        categories.value = listOf(
            Category("Compras"),
            Category("Auto"),
            Category("Otros"),
        )
    }

    fun getCategories(): LiveData<List<Category>> {
        return categories
    }

    fun addCategory(category: Category) {
        val currentList = categories.value?.toMutableList() ?: mutableListOf()
        currentList.add(category)
        categories.value = currentList
    }
}