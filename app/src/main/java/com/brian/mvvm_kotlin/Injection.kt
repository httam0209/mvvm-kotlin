package com.brian.mvvm_kotlin

import android.content.Context
import com.brian.mvvm_kotlin.earthquakes.EarthquakeInteractor
import com.brian.mvvm_kotlin.earthquakes.EarthquakeListViewModelFactory
import com.brian.mvvm_kotlin.network.ApiClientService
import com.brian.mvvm_kotlin.network.EarthquakeApi
import com.brian.mvvm_kotlin.network.EarthquakeService

/**
 * @author Brian
 * @date: 6/12/18
 */


class Injection {

    companion object {

        fun provideEarthquakeApi(context: Context) : EarthquakeApi {
            return ApiClientService.getInstance().createService(context).create(EarthquakeApi::class.java)
        }

        fun provideEarthquakeInteractor(context: Context) : EarthquakeInteractor {
            return EarthquakeInteractor(EarthquakeService(provideEarthquakeApi(context)))
        }

        fun provideViewModelFactory(context: Context) : EarthquakeListViewModelFactory {
            return EarthquakeListViewModelFactory(provideEarthquakeInteractor(context))
        }
    }
}