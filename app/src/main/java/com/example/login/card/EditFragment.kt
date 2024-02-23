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
import com.example.login.card.data.cardInfo
import com.testing.login.R
import com.testing.login.databinding.FragmentCardsBinding
import com.testing.login.databinding.FragmentEditBinding

class EditFragment : Fragment(), CardAdapter.CardClickListener {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardAdapter : CardAdapter
    private val viewModel : CardViewModel by activityViewModels()
    override fun onClicked(itemId: Int) {
        Log.d("nothing", "donothing")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.cardList.observe(viewLifecycleOwner, Observer { newList ->
//            cardAdapter.submitList(newList)
//        })
        val argumentValue = requireArguments().getInt("getID", 1)
        binding.item.setText(viewModel.getCardInfo(argumentValue).item)
        binding.description.setText(viewModel.getCardInfo(argumentValue).description)
        binding.timeSlot.setText(viewModel.getCardInfo(argumentValue).time)
        binding.color.setText(viewModel.getCardInfo(argumentValue).color)
        cardAdapter = CardAdapter(this)
        binding.submit.setOnClickListener {
            viewModel.modifyCard(cardInfo(argumentValue+1, binding.item.text.toString(), binding.description.text.toString(), binding.timeSlot.text.toString(), binding.color.text.toString(),viewModel.currentWorkspace))
            findNavController().navigate(R.id.cards)
        }
        binding.delete.setOnClickListener{
            viewModel.deleteCard(argumentValue)
            cardAdapter.notifyItemChanged(argumentValue)
            findNavController().navigate(R.id.cards)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}