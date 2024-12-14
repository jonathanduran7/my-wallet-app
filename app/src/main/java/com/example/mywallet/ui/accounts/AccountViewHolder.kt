package com.example.mywallet.ui.accounts

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.R

class AccountViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val tvName: TextView = view.findViewById(R.id.tvName)
    private val tvCurrency: TextView = view.findViewById(R.id.tvCurrency)

    fun bind(account: Account){
        tvName.text = account.name
        tvCurrency.text = account.currency
    }
}