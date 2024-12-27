package com.example.mywallet.ui.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.R

//class AccountAdapter(private var accounts: List<Account>) : RecyclerView.Adapter<AccountViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
//        return AccountViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return accounts.size
//    }
//
//    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
//        holder.bind(accounts[position])
//    }
//
//    fun updateData(newAccounts: List<Account>) {
//        val diffCallback = AccountDiffCallback(this.accounts, newAccounts)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//
//        this.accounts = newAccounts
//        diffResult.dispatchUpdatesTo(this)
//    }
//}

class AccountAdapter : ListAdapter<Account, AccountViewHolder>(AccountDiffCallbackTwo()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}