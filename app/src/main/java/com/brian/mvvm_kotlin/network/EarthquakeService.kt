package com.brian.mvvm_kotlin.network

import com.brian.mvvm_kotlin.data.Earthquake
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author Brian
 * @date: 6/11/18
 */


class EarthquakeService(private var earthquakeApi: EarthquakeApi) {

    fun getEarthQuakes(): Single<MutableList<Earthquake>> {
        return earthquakeApi.getEarthquakes(
                "geojson", "2014-01-01", "2014-01-02", "4")
                .flatMap {
                    Observable.fromIterable(it.features)
                            .map {
                                val earthquake = Earthquake()
                                earthquake.detail = it.properties?.detail
                                earthquake.place = it.properties?.place
                                earthquake.magnitude = it.properties?.mag
                                earthquake.time = it.properties?.time
                                earthquake.url = it.properties?.url
                                earthquake.detail = it.properties?.detail
                                earthquake.status = it.properties?.status
                                earthquake.type = it.properties?.type
                                return@map earthquake
                            }.toList()
                }

    }

}