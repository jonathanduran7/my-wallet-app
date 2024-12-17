package com.example.mywallet.ui.category

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.R


class CategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val tvNameCategory: TextView = view.findViewById(R.id.tvNameCategory)

    fun bind(category: Category) {
        Log.i("CategoryViewHolder", "Binding category: $category")
        tvNameCategory.text = category.name
    }

}