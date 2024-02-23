package com.example.login.card

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.login.card.data.CardViewModel
import com.example.login.card.data.WorkspaceInfo
import com.example.login.card.data.cardInfo
import com.testing.login.R
import com.testing.login.databinding.FragmentAddWorkspaceBinding
import com.testing.login.databinding.FragmentEditBinding

class addWorkspace : Fragment(), WorkspaceAdapter.workspaceClickListener{

    private var _binding: FragmentAddWorkspaceBinding? = null
    private val binding get() = _binding!!
    private lateinit var workspaceAdapter: WorkspaceAdapter
    private val viewModel : CardViewModel by activityViewModels()
    override fun WSonClicked(itemId: String) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddWorkspaceBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.cardList.observe(viewLifecycleOwner, Observer { newList ->
//            cardAdapter.submitList(newList)
//        })

        workspaceAdapter = WorkspaceAdapter(this)
        binding.submitW.setOnClickListener {
            viewModel.addWorkspace(WorkspaceInfo(workspaceAdapter.itemCount + 1,binding.itemW.text.toString()))
            findNavController().navigate(R.id.action_addWorkspace2_to_cards)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}