package com.example.mywallet.ui.accounts

import android.app.Dialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        binding.fabAddAccount.setOnClickListener {
            showDialog()
        }

        initUI()

        return root
    }

    private fun initUI(){
        accountAdapter = AccountAdapter(accounts)
        binding.rvAccount.setHasFixedSize(true)
        binding.rvAccount.adapter = accountAdapter
        binding.rvAccount.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    private fun showDialog(){
        Log.i("AccountFragment", "Showing dialog")
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_account)

        dialog.setCancelable(true)

        val etAccount: TextView = dialog.findViewById(R.id.etAccount)

        val etBalance: TextView = dialog.findViewById(R.id.etBalance)

        val btnSave: TextView = dialog.findViewById(R.id.btnSave)

        val spCurrency = dialog.findViewById<Spinner>(R.id.spCurrency)

        var optionCurrency = ""

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currencies,
            android.R.layout.simple_spinner_item
        )

        spCurrency.adapter = adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = parent?.getItemAtPosition(position).toString()
                optionCurrency = selectedOption
                Log.i("AccountFragment", "Selected option: $selectedOption")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Manejar caso de no seleccionar nada, si aplica
            }
        }

        btnSave.setOnClickListener {
            val balance = etBalance.text.toString().toDouble()
            accounts.add(Account(etAccount.text.toString(), balance, optionCurrency))
            accountAdapter?.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.show()
    }
}