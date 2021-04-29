package com.yours.masterapp.ui.calculation

import androidx.lifecycle.ViewModel
import com.yours.masterapp.domain.CurrencyUseCase

class CalculationViewModel(
    private val currencyRateUseCase: CurrencyUseCase
) : ViewModel() {

    fun getCurrentAmount(rate:Float,amount:Int) : Float{
        return currencyRateUseCase.getCurrencyRateFrom(rate,amount)
    }

}