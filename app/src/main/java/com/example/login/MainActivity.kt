package com.example.login

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.card.CardAdapter
import com.example.login.card.WorkspaceAdapter
import com.example.login.card.data.CardViewModel
import com.example.login.card.data.WorkspaceInfo
import com.testing.login.R
import com.testing.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CardAdapter.CardClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var workspaceInfo: WorkspaceInfo
    private val bundle = Bundle()
    private lateinit var cardAdapter : CardAdapter

    override fun onClicked(itemId: Int) {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        binding.add.setOnClickListener {
            navController.navigate(R.id.action_cards_to_addFragment)
        }

        //Workspace Code:


    }
}
