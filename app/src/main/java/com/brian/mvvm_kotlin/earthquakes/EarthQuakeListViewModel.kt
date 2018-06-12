package com.brian.mvvm_kotlin.earthquakes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.brian.mvvm_kotlin.data.Earthquake
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Brian
 * @date: 6/11/18
 */


class EarthQuakeListViewModel(private var earthquakeInteractor: EarthquakeInteractor) : ViewModel() {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private var earthquakeList: MutableLiveData<MutableList<Earthquake>> = MutableLiveData()

    private var error: MutableLiveData<Throwable> = MutableLiveData()

    fun getLoadingStatus(): LiveData<Boolean> {
        return isLoading
    }

    fun earthQuakes(): LiveData<MutableList<Earthquake>> {
        return earthquakeList
    }

    fun getError(): LiveData<Throwable> {
        return error
    }

    fun loadEarthQuakes() {
        mCompositeDisposable.add(
                earthquakeInteractor.loadEarthQuakes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            isLoading.value = true
                        }
                        .doAfterTerminate {
                            isLoading.value = false
                        }
                        .subscribe(
                                { t -> earthquakeList.value = t },

                                { t -> error.value = t }
                        )
        )
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}