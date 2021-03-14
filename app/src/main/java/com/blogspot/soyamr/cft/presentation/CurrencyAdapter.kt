package com.blogspot.soyamr.cft.presentation

import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.cft.R
import com.blogspot.soyamr.cft.databinding.RecyclerviewItemBinding
import com.blogspot.soyamr.cft.domain.model.Currency
import com.google.android.material.textfield.TextInputEditText


class CurrencyAdapter(private val listener: (String, Int) -> Unit) :
    ListAdapter<Currency, CurrencyAdapter.ViewHolder>(CurrencyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val listener: (String, Int) -> Unit,
        private val binding: RecyclerviewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            with(binding) {
                setListeners(currency, editTextNumber)
                nameTextView.text = currency.name
                charCodeTextView.text = currency.charCode
                editTextNumber.setText(currency.nominal.toString())
                textInputLayout.suffixText =
                    binding.root.resources.getString(
                        R.string.ruble,
                        "%.5f".format(currency.value * currency.nominal).toString()
                    )
            }
        }

        private fun setListeners(currency: Currency, editTextNumber: TextInputEditText) {
            editTextNumber.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editTextNumber.clearFocus()
                }
                false
            }

            editTextNumber.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (!editTextNumber.text.isNullOrEmpty())
                        listener(currency.id, editTextNumber.text.toString().toInt())
                }
            }

        }

        companion object {
            fun from(parent: ViewGroup, listener: (String, Int) -> Unit): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(listener, binding)
            }
        }
    }
}


class CurrencyDiffCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

}