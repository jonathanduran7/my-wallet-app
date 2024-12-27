package com.example.mywallet.ui.accounts

import androidx.recyclerview.widget.DiffUtil

class AccountDiffCallbackTwo: DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}