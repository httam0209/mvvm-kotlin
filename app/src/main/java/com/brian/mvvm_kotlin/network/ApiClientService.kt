package com.brian.mvvm_kotlin.network

import android.content.Context
import com.brian.mvvm_kotlin.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Brian
 * @date: 6/10/18
 */


class ApiClientService private constructor() {

    private fun makeCache(context: Context): Cache {
        val CACHE_SIZE = 10 * 1024 * 1024  // 10 MB
        return Cache(context.applicationContext.cacheDir, CACHE_SIZE.toLong())
    }

    private fun makeOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(makeCache(context))
                .addInterceptor(makeLoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS).build();
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun gsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    fun createService(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(makeOkHttpClient(context))
                .addConverterFactory(gsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    companion object {

        fun getInstance(): ApiClientService {
            return SingleHelper().INSTANCE
        }

        class SingleHelper {
            val INSTANCE = ApiClientService()
        }
    }
}