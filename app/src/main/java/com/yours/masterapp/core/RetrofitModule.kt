package com.yours.masterapp.data

import android.content.Context
import com.yours.masterapp.BuildConfig
import kotlinx.serialization.UnstableDefault
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.io.File


private const val CACHE_FILE_SIZE: Long = 30 * 1000 * 1000 // 30 Mib
val retrofitModule = module {

    single<Call.Factory> {
        val cacheFile = cacheFile(androidContext())
        val cache = cache(cacheFile)
        okhttp(cache)
    }

    single {
        retrofit(get(), BuildConfig.BASE_URL)
    }

    single {
        get<Retrofit>().create(ServiceRemote::class.java)
    }
}

private val interceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

private fun cacheFile(context: Context) = File(context.filesDir, "my_own_created_cache").apply {
    if (!this.exists())
        mkdirs()
}

private fun cache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)

@UseExperimental(UnstableDefault::class)
private fun retrofit(callFactory: Call.Factory, baseUrl: String) = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(baseUrl)
    .addConverterFactory(
        GsonConverterFactory.create()
    )
    .build()

private fun okhttp(cache: Cache) = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .cache(cache)
    .build()
