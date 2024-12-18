package com.example.mywallet.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mywallet.databinding.FragmentHomeBinding
import com.example.mywallet.ui.components.CustomButton

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        setListener()
        initUI()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListener(){
        binding.btnHome.setOnClickListener{
            binding.textHome.text = "Hola"
            Log.i("HomeFragment", "Button clicked")
        }
    }

    private fun initUI(){
        val myCustomButton = CustomButton(binding.root.context).apply {
            setCustomStyle(
                text = "Desde el fragment",
                textSize = 16f,
                backgroundColor = android.R.color.black,
                textColor = android.R.color.white
            )
        }

        binding.myHomeFragment.addView(myCustomButton)
    }
}