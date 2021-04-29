package com.yours.masterapp.ui.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yours.masterapp.R
import com.yours.masterapp.data.CurrencyRateResponse
import com.yours.masterapp.domain.CurrencyUseCase
import com.yours.masterapp.utils.Event
import kotlinx.coroutines.launch

class ListViewModel(
    private val currencyRateUseCase: CurrencyUseCase
) : ViewModel() {

    val viewState = ListViewState()

    public fun getList() {
        viewModelScope.launch {
            runCatching {
                emitUiState(showProgress = true)
                currencyRateUseCase.featchCurrencyRate()
            }.onSuccess {
                emitUiState(currencyRates = Event(it))
            }.onFailure {
                it.printStackTrace()
                emitUiState(error = Event(R.string.internet_failure_error))
            }
        }
    }

    private fun emitUiState(
        showProgress: Boolean = false,
        currencyRates: Event<CurrencyRateResponse>? = null,
        error: Event<Int>? = null
    ) {
        val dataState = ListDataState(showProgress, currencyRates, error)
        viewState._currencyListDataState.value = dataState
    }
}