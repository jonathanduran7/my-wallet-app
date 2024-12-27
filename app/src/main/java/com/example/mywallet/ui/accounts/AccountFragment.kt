package com.example.mywallet.ui.accounts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.R
import com.example.mywallet.databinding.DialogAccountBinding
import com.example.mywallet.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountViewModel by viewModels()
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupSearchView()
        setupFab()
    }

    private fun setupRecyclerView() {
        accountAdapter = AccountAdapter(
            listOf(
                Account("Mercado Pago2", 100.0, "ARS"),
                Account("Banco Galicia", 200.0, "ARS"),
                Account("Efectivo", 300.0, "ARS")
            )
        )
        binding.rvAccount.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        viewModel.accounts.observe(viewLifecycleOwner, Observer { accounts ->
            accountAdapter.updateData(accounts)
        })
    }

    private fun setupSearchView() {
        binding.svAccount.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.addQuery(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Puedes implementar filtrado en tiempo real aquí si lo deseas
                return true
            }
        })
    }

    private fun setupFab() {
        binding.fabAddAccount.setOnClickListener {
            showAddAccountDialog()
        }
    }

    private fun showAddAccountDialog() {
        val dialogBinding = DialogAccountBinding.inflate(layoutInflater)
        val currencyAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currencies,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        dialogBinding.spCurrency.adapter = currencyAdapter

        var selectedCurrency = currencyAdapter.getItem(0) ?: ""

        dialogBinding.spCurrency.setOnItemSelectedListener { position ->
            selectedCurrency = currencyAdapter.getItem(position) ?: ""
        }

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.add_account))
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.save)) { dialog, _ ->
                val accountName = dialogBinding.etAccount.text.toString().trim()
                val balanceText = dialogBinding.etBalance.text.toString().trim()
                val balance = balanceText.toDoubleOrNull() ?: 0.0

                if (accountName.isNotEmpty()) {
                    val newAccount = Account(accountName, balance, selectedCurrency.toString())
                    viewModel.addAccount(newAccount)
                    accountAdapter.notifyDataSetChanged()
                }
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Extensión para manejar el listener del Spinner de manera más concisa
fun Spinner.setOnItemSelectedListener(onItemSelected: (Int) -> Unit) {
    this.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: android.widget.AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            onItemSelected(position)
        }

        override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
    }
}
