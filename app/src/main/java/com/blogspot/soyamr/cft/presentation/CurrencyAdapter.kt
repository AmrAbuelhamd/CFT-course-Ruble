package com.blogspot.soyamr.cft.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.cft.R
import com.blogspot.soyamr.cft.databinding.RecyclerviewItemBinding
import com.blogspot.soyamr.cft.domain.model.Currency
import java.util.*

class CurrencyAdapter() : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
    private var dataList: List<Currency> = Collections.emptyList()

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setData(dataList: List<Currency>) {
        if (!dataList.isNullOrEmpty()) {
            this.dataList = dataList
            notifyDataSetChanged()
        }
    }

    class ViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            with(binding) {
                nameTextView.text = currency.name
                charCodeTextView.text = currency.charCode
                sumInRubles.text =
                    binding.root.resources.getString(R.string.ruble, currency.value.toString())
                editTextNumber.setText("1")
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}