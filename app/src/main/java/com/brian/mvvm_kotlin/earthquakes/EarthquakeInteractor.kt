package com.brian.mvvm_kotlin.earthquakes

import com.brian.mvvm_kotlin.data.Earthquake
import com.brian.mvvm_kotlin.network.EarthquakeService
import io.reactivex.Single

/**
 * @author Brian
 * @date: 6/11/18
 */


class EarthquakeInteractor(private var earthquakeService: EarthquakeService) {

    fun loadEarthQuakes(): Single<MutableList<Earthquake>> {
        return earthquakeService.getEarthQuakes()
    }
}