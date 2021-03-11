package com.blogspot.soyamr.cft.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.soyamr.cft.domain.interactors.GetCurrenciesUseCase
import com.blogspot.soyamr.cft.domain.model.Currency
import com.blogspot.soyamr.cft.domain.model.onFailure
import com.blogspot.soyamr.cft.domain.model.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(getCurrenciesUseCase: GetCurrenciesUseCase) :
    ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading


    private val _currencies: MutableLiveData<List<Currency>> = MutableLiveData()
    val currencies: LiveData<List<Currency>> = _currencies

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    init {
        //todo get data (getCurrenciesUseCase)
        viewModelScope.launch {
            _isLoading.value = true
            getCurrenciesUseCase()
                .onSuccess {
                    _currencies.value = it
                }
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false

        }
    }

    fun updateData() {
        //TODO -> call update-use-case
        //          call get-data which will get data from database,
        //                  -if no data in database then get data from internet
    }


}