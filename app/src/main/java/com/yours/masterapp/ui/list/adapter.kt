package com.yours.masterapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yours.masterapp.data.CurrencyItem
import com.yours.masterapp.databinding.ItemListBinding

class ListAdapter(val currencyRates: List<CurrencyItem>,val itemListener:ItemClickable) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtCurrencyName.text = currencyRates[position].name
        holder.binding.txtCurrencyValue.text = currencyRates[position].value
        holder.binding.lnItem.setOnClickListener{
            itemListener.onItemClick(currencyRates[position])
        }
    }

    override fun getItemCount(): Int {
        return currencyRates.size
    }
}

interface ItemClickable {
    fun onItemClick(item: CurrencyItem)
}