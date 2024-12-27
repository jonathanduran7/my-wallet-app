package com.example.mywallet.ui.movements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mywallet.databinding.FragmentMovementBinding

class MovementFragment: Fragment() {
    private var _binding: FragmentMovementBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movementViewModel = ViewModelProvider(this).get(MovementViewModel::class.java)

        _binding = FragmentMovementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMovement
        movementViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}