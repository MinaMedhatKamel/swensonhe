package com.yours.masterapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yours.masterapp.data.CurrencyRateResponse
import com.yours.masterapp.utils.Event

class ListViewState {
    val _currencyListDataState = MutableLiveData<ListDataState>()
    val currencyListDataState: LiveData<ListDataState> get() = _currencyListDataState
}

data class ListDataState(
    val showProgress: Boolean,
    val currencyRates: Event<CurrencyRateResponse>?,
    val error: Event<Int>?
)