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
import douglasspgyn.com.github.circularcountdown.CircularCountdown
import douglasspgyn.com.github.circularcountdown.listener.CircularListener
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
        setUpCounter()
    }

    private fun setUpCounter() {
        viewBinding.circularCountdown.create(0, 60, CircularCountdown.TYPE_SECOND)
            .listener(object : CircularListener {
                override fun onTick(progress: Int) {
                }

                override fun onFinish(newCycle: Boolean, cycleCount: Int) {
                    viewModel.updateData()
                }
            })
            .start()
    }

    private fun setUpRecycler() {
        adapter = CurrencyAdapter() { id: String, value: Int ->
            viewModel.updateNominalOf(id, value)
        }
        viewBinding.list.adapter = adapter
    }

    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(this, ::changeLoadingState)
        viewModel.currencies.observe(this, ::updateRecyclerView)
        viewModel.error.observe(this, ::showError)
        viewModel.lastUpdated.observe(this, ::updateText)
    }

    private fun updateText(date: String?) {
        date?.let {
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