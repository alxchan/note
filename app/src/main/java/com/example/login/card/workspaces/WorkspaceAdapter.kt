package com.example.login.card

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.card.data.WorkspaceInfo
import com.example.login.card.data.cardInfo
import com.testing.login.databinding.AddbuttonBinding
import com.testing.login.databinding.CardtemplateBinding
import com.testing.login.databinding.WorkspaceBinding

class WorkspaceAdapter(private val WorkspaceClickListener: workspaceClickListener) : ListAdapter<WorkspaceInfo, RecyclerView.ViewHolder>(WorkspaceDiffCallback()){
    var dataList: MutableList<WorkspaceInfo> = mutableListOf()
    interface workspaceClickListener{
        fun WSonClicked(itemId: String)
    }
    private val VIEW_TYPE_BUTTON = 1
    private val VIEW_TYPE_ITEM = 2
    override fun getItemViewType(position: Int): Int {
        return if(position != dataList.size){
            VIEW_TYPE_ITEM
        } else{
            VIEW_TYPE_BUTTON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("getSize", "${itemCount}")
        return if(viewType == VIEW_TYPE_BUTTON){
            val binding = AddbuttonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            Log.d("button", "created")
            return ButtonViewHolder(binding)
        } else{
            Log.d("not button", "${viewType}")
        val binding = WorkspaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkspaceViewholder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType){
            VIEW_TYPE_BUTTON -> {
                val buttonViewholder = holder as ButtonViewHolder
                buttonViewholder.itemView.setOnClickListener{
                    WorkspaceClickListener.WSonClicked("9876574192385781934887391853781853123943279814372")
                }
            }
            VIEW_TYPE_ITEM ->{
                val workspace = dataList[position]
                val holder = holder as WorkspaceViewholder
                holder.bind(workspace)
                holder.itemView.setOnClickListener{
                WorkspaceClickListener.WSonClicked(dataList[position].name)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size  + 1
    }
    inner class WorkspaceViewholder(private val binding: WorkspaceBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(workspace : WorkspaceInfo){
            binding.workspaceButton.text = workspace.name
        }
    }

    inner class ButtonViewHolder(private val binding: AddbuttonBinding) : RecyclerView.ViewHolder(binding.root){

    }
    class WorkspaceDiffCallback: DiffUtil.ItemCallback<WorkspaceInfo>() {
        override fun areItemsTheSame(oldItem: WorkspaceInfo, newItem: WorkspaceInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WorkspaceInfo, newItem: WorkspaceInfo): Boolean {
            return oldItem == newItem
        }
    }
}

