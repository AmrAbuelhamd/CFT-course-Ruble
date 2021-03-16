package com.blogspot.soyamr.cft.presentation

import androidx.lifecycle.*
import com.blogspot.soyamr.cft.domain.interactors.*
import com.blogspot.soyamr.cft.domain.model.onFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
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


    val currencies =
        getCurrenciesUseCase()
            .onStart { _isLoading.value = true }
            .asLiveData()

    val lastUpdated =
        getLastUpdatedDateUseCase()
            .onStart { _isLoading.value = true }
            .asLiveData()

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            updateCurrenciesUseCase()
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false
        }
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