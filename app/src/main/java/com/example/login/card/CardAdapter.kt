package com.example.login.card

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.card.data.cardInfo
import com.testing.login.R
import com.testing.login.databinding.CardtemplateBinding

class CardAdapter(private val cardClickListener: CardClickListener?) : ListAdapter<cardInfo, CardAdapter.CardViewHolder>(CardDiffCallback()){
    val dataList: MutableList<cardInfo> = mutableListOf()
    interface CardClickListener{
        fun onClicked(itemId: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardtemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = dataList[position]
        holder.bind(card)
        holder.itemView.setOnClickListener{
            cardClickListener?.onClicked(position)
            //navController?.navigate((R.id.action_cards_to_SecondFragment),bundle)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
inner class CardViewHolder(private val binding: CardtemplateBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(card : cardInfo){
        binding.itemv.text = card.item
        binding.descriptionv.text = card.description
        binding.timeSlotv.text = card.time
        binding.parentCard.setBackgroundColor(Color.parseColor(if(card.color != "") card.color else "#222222" ));

    }
}
    class CardDiffCallback: DiffUtil.ItemCallback<cardInfo>() {
        override fun areItemsTheSame(oldItem: cardInfo, newItem: cardInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: cardInfo, newItem: cardInfo): Boolean {
            return oldItem == newItem
        }
    }
}

