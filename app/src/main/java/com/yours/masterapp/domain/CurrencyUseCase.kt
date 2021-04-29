package com.yours.masterapp.domain

import com.yours.masterapp.BuildConfig
import com.yours.masterapp.data.CurrencyRateResponse
import com.yours.masterapp.data.ServiceRemote

interface CurrencyUseCase {
    suspend fun featchCurrencyRate(): CurrencyRateResponse
    fun getCurrencyRateFrom(rate: Float, amount: Int) : Float
}

class CurrencyUseCaseImp(val remote: ServiceRemote) : CurrencyUseCase {
    override suspend fun featchCurrencyRate(): CurrencyRateResponse {
        return remote.getCurrencyRates(BuildConfig.access_key)
    }

    override fun getCurrencyRateFrom(rate: Float, amount: Int) : Float {
        return amount * rate
    }
}