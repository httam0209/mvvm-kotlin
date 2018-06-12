package com.brian.mvvm_kotlin.earthquakes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * @author Brian
 * @date: 6/11/18
 */


class EarthquakeListViewModelFactory(private var earthquakeInteractor: EarthquakeInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EarthQuakeListViewModel::class.java)) {
            return EarthQuakeListViewModel(earthquakeInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
