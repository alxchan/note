package com.example.login.card

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.card.data.CardViewModel
import com.testing.login.R
import com.testing.login.databinding.FragmentCardsBinding

class cards : Fragment(), CardAdapter.CardClickListener, WorkspaceAdapter.workspaceClickListener{
    private var _binding : FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private lateinit var adapterv2: WorkspaceAdapter
    private val viewModel : CardViewModel by activityViewModels()
    private var clicked = false
    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun WSonClicked(itemId: String) {
        if(itemId.isDigitsOnly() && itemId.isNotBlank()){
            findNavController().navigate(R.id.action_cards_to_addWorkspace2)
        }
        viewModel.currentWorkspace = itemId
        viewModel.refresh()
        adapter.dataList.clear()
        adapter.dataList.addAll(viewModel.cardList.value!!)
        adapter.notifyDataSetChanged()
    }

    override fun onClicked(itemId: Int) {
        bundle.putInt("getID", itemId)
        findNavController().navigate(R.id.action_cards_to_SecondFragment, bundle)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.cardList
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CardAdapter(this)
        recyclerView.adapter = adapter
        adapter.dataList.addAll(viewModel.cardList.value!!)

        //Workspaces code:
        val WSrecyclerView = binding.workspaces
        adapterv2 = WorkspaceAdapter(this)
        WSrecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        WSrecyclerView.adapter = adapterv2
        adapterv2.dataList.addAll(viewModel.workspaceList.value!!)
        System.out.println(adapterv2.dataList.size)
        Log.d("adapterv2", "${adapterv2.dataList}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
