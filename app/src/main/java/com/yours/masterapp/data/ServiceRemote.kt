package com.yours.masterapp.data

import com.yours.masterapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceRemote {
    @GET("api/latest")
    suspend fun getCurrencyRates(
            @Query("access_key") accessKey: String,
    ): CurrencyRateResponse
}