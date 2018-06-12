package com.brian.mvvm_kotlin.callback;


import com.brian.mvvm_kotlin.data.Earthquake;

/**
 * @author Brian
 * @date: 6/10/18
 */


public interface EarthQuakeItemCallback {
    void onItemClick(Earthquake earthquake);
}
