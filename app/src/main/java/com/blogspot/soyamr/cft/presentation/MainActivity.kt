package com.blogspot.soyamr.cft.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blogspot.soyamr.cft.R
import com.blogspot.soyamr.cft.databinding.ActivityMainBinding
import com.blogspot.soyamr.cft.domain.Repository
import com.blogspot.soyamr.cft.domain.model.Currency
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var repository: Repository

    val viewModel: MainActivityViewModel by viewModels()

    private val viewBinding: ActivityMainBinding by viewBinding()

    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpRecycler()
        setUpClickListeners()
        setUpViewModelObservers()
    }

    private fun setUpRecycler() {
        adapter = CurrencyAdapter()
        viewBinding.list.adapter = adapter
    }

    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(this, ::changeLoadingState)
        viewModel.currencies.observe(this, ::updateRecyclerView)
        viewModel.error.observe(this, ::showError)
    }

    private fun showError(error: String?) {
        error?.let {
            if (error.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRecyclerView(currencies: List<Currency>?) {
        currencies?.let {
            adapter.setData(it)
        }
    }

    private fun changeLoadingState(state: Boolean?) {
        state?.let {
            viewBinding.mySwipeToRefresh.isRefreshing = it
        }
    }

    private fun setUpClickListeners() {
        viewBinding.mySwipeToRefresh.setOnRefreshListener {

            viewModel.updateData()
        }


    }
}