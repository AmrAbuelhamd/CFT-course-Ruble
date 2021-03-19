package com.blogspot.soyamr.cft.presentation

import android.os.Bundle
import android.view.View
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

    private val viewModel: MainActivityViewModel by viewModels()

    private val viewBinding: ActivityMainBinding by viewBinding()

    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpRecycler()
        setUpClickListeners()
        setUpViewModelObservers()
    }


    override fun onResume() {
        super.onResume()
        viewModel.startCountingDown()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopCountingDown()
        viewModel.removeErrorMessages()
    }

    private fun setUpRecycler() {
        adapter = CurrencyAdapter() { id: String, value: Int ->
            viewModel.updateNominalOf(id, value)
        }
        viewBinding.list.adapter = adapter
    }


    private fun setUpViewModelObservers() {
//        println("observing data")
        viewModel.isLoading.observe(this, ::changeLoadingState)
        viewModel.isLoadingDate.observe(this, ::changeDateTextLoadingState)
        viewModel.currencies.observe(this, ::updateRecyclerView)
        viewModel.error.observe(this, ::showError)
        viewModel.lastUpdated.observe(this, ::updateDateText)
        viewModel.counter.observe(this, ::updateCounter)
    }

    private fun changeDateTextLoadingState(visibility: Boolean?) {
        visibility?.let {
            if (it) {
                viewBinding.dateTextView.visibility = View.INVISIBLE
                viewBinding.progressBar.visibility = View.VISIBLE
            } else {
                viewBinding.progressBar.visibility = View.GONE
                viewBinding.dateTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun updateCounter(i: Int?) {
        i?.let {
            viewBinding.counterTextView.text = it.toString()
            viewBinding.circularCountdown.setProgressCompat(5 * it / 3, true)
        }
    }

    private fun updateDateText(date: String?) {
        date?.let {
            viewBinding.progressBar.visibility = View.GONE
            viewBinding.dateTextView.visibility = View.VISIBLE
            viewBinding.dateTextView.text = it
        }
    }

    private fun showError(error: String?) {
        error?.let {
            if (error.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRecyclerView(currencies: List<Currency>?) {
        currencies.let {
            viewBinding.mySwipeToRefresh.isRefreshing = false
            adapter.submitList(it)
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