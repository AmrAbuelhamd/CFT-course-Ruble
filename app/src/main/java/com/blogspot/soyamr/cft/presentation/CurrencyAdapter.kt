package com.blogspot.soyamr.cft.presentation

import android.view.*
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.cft.R
import com.blogspot.soyamr.cft.databinding.RecyclerviewItemBinding
import com.blogspot.soyamr.cft.domain.model.Currency
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class CurrencyAdapter(private val listener: (Currency, Int) -> Unit) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
    private var dataList: List<Currency> = Collections.emptyList()

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)

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

    class ViewHolder(
        private val listener: (Currency, Int) -> kotlin.Unit,
        private val binding: RecyclerviewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            with(binding) {
                setListeners(currency,editTextNumber)
                nameTextView.text = currency.name
                charCodeTextView.text = currency.charCode
                editTextNumber.setText("1")
                textInputLayout.suffixText =
                    binding.root.resources.getString(R.string.ruble, currency.value.toString())
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
                        listener(currency, editTextNumber.text.toString().toInt())
                }
            }

        }

        companion object {
            fun from(parent: ViewGroup, listener: (Currency, Int) -> Unit): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(listener, binding)
            }
        }
    }
}