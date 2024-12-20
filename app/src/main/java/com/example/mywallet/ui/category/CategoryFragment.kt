package com.example.mywallet.ui.category

import android.app.Dialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.R
import com.example.mywallet.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null

    private val binding get() = _binding!!

    private var categoryAdapter: CategoryAdapter? = null
    private lateinit var viewModel: CategoryViewModel

    private var categories = mutableListOf<Category>(
        Category("Compras"),
        Category("Auto"),
        Category("Otros"),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater,container,false)

        val root: View = binding.root

      //TODO: implement search view

        initUI()

        viewModel.getCategories().observe(viewLifecycleOwner) {
            categories.clear()
            categories.addAll(it)
            categoryAdapter?.notifyDataSetChanged()
        }

        binding.fabAddCategory.setOnClickListener {
            showDialog()
        }

        return root
    }

    private fun initUI(){
        categoryAdapter = CategoryAdapter(categories)
        binding.rvCategory.setHasFixedSize(true)
        binding.rvCategory.adapter = categoryAdapter
        binding.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun showDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_category)

        dialog.setCancelable(true)

        val etAccount: TextView = dialog.findViewById(R.id.etCategory)

        val btnSave: TextView = dialog.findViewById(R.id.btnSaveCategory)

        btnSave.setOnClickListener {
            if (etAccount.text.toString().isNotEmpty()) {
//                categories.add(Category(etAccount.text.toString()))
                viewModel.addCategory(Category(etAccount.text.toString()))
                categoryAdapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}