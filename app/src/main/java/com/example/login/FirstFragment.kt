package com.example.login

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.testing.login.R
import com.testing.login.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        val button : Button? = binding.card
        val layoutParams = LinearLayout.LayoutParams(
            (250* Resources.getSystem().displayMetrics.density).toInt(), (100* Resources.getSystem().displayMetrics.density).toInt()
        )
        layoutParams.gravity = Gravity.CENTER
        button?.layoutParams = layoutParams
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        binding.card.setOnClickListener {
            if(!sharedViewModel.isNavigated){
                findNavController().navigate(R.id.action_blankFragment_to_SecondFragment)
                sharedViewModel.isNavigated = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}