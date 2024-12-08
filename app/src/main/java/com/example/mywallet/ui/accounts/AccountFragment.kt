package com.example.mywallet.ui.accounts

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.R
import com.example.mywallet.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    private val binding get() = _binding!!

    private var accountAdapter: AccountAdapter? = null

    private var accounts = mutableListOf<Account>(
        Account("Mercado Pago", 100.0, "ARS"),
        Account("Banco Galicia", 200.0, "ARS"),
        Account("Efectivo", 300.0, "ARS"),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val svAccount: SearchView = binding.svAccount

        svAccount.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    accountViewModel.addQuery(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        initUI()

        return root
    }

    private fun initUI(){
        accountAdapter = AccountAdapter(accounts)
        binding.rvAccount.setHasFixedSize(true)
        binding.rvAccount.adapter = accountAdapter
        binding.rvAccount.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}