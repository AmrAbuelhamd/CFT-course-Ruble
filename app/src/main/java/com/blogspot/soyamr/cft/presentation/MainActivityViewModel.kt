package com.blogspot.soyamr.cft.presentation

import androidx.lifecycle.*
import com.blogspot.soyamr.cft.domain.interactors.*
import com.blogspot.soyamr.cft.domain.model.onFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val updateCurrenciesUseCase: UpdateCurrenciesUseCase,
    private val updateCurrencyNominalUseCase: UpdateCurrencyNominalUseCase,
    private val updateCurrenciesFromApiUseCase: UpdateCurrenciesFromApiUseCase,
    private val getLastUpdatedDateUseCase: GetLastUpdatedDateUseCase,
) :
    ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadingDate = MutableLiveData(false)
    val isLoadingDate: LiveData<Boolean> = _isLoadingDate


    val currencies =
        getCurrenciesUseCase()
            .onStart { _isLoading.value = true }
            .asLiveData()

    val lastUpdated =
        getLastUpdatedDateUseCase()
            .onStart { _isLoadingDate.value = true }
            .asLiveData()

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    var job: Job? = null

    fun startCountingDown() {
        job = viewModelScope.launch {
            while (true) {
                _counter.value = _counter.value?.plus(1)
                if (_counter.value == 60) {
                    updateData()
                    _counter.value = 0
                }
                delay(1000)
            }
        }
    }

    fun stopCountingDown() {
        job?.cancel()
    }

    init {
        viewModelScope.launch {
            _isLoading.value = true
            updateCurrenciesUseCase()
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false
        }
//        countDown.start()
//        countDown.cancel()
    }

    fun updateData() {
        viewModelScope.launch {
            _isLoading.value = true
            updateCurrenciesFromApiUseCase()
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false
        }
    }

    fun updateNominalOf(id: String, value: Int) {
        viewModelScope.launch {
            updateCurrencyNominalUseCase(id, value)
        }
    }


}