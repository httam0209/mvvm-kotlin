package com.brian.mvvm_kotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.brian.mvvm_kotlin.callback.EarthQuakeItemCallback
import com.brian.mvvm_kotlin.data.Earthquake
import com.brian.mvvm_kotlin.databinding.ActivityMainBinding
import com.brian.mvvm_kotlin.earthquakes.EarthQuakeAdapter
import com.brian.mvvm_kotlin.earthquakes.EarthQuakeListViewModel
import com.brian.mvvm_kotlin.earthquakes.EarthquakeListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EarthQuakeItemCallback {

    private lateinit var viewModelFactory: EarthquakeListViewModelFactory
    private lateinit var mEarthQuakeModel: EarthQuakeListViewModel
    private lateinit var mAdapter: EarthQuakeAdapter
    private var mEarthQuakes: MutableList<Earthquake> = mutableListOf()
    private var mMainActivityBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModelFactory = Injection.provideViewModelFactory(this)

        mEarthQuakeModel = ViewModelProviders.of(this, viewModelFactory).get(EarthQuakeListViewModel::class.java)

        setUpEarthRecycler()

        mEarthQuakeModel.loadEarthQuakes()

        observableLoadingStatus()

        observableEarthQuakes()

        observableError()

    }

    private fun setUpEarthRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        mAdapter = EarthQuakeAdapter(this, mEarthQuakes, this)
        recyclerView.adapter = mAdapter
    }

    private fun observableLoadingStatus() {
        mEarthQuakeModel.getLoadingStatus().observe(this, Observer<Boolean>
        { isLoading ->
            mMainActivityBinding?.loadingIndicator?.visibility = isLoading.let {
                if (it!!) View.VISIBLE else View.GONE
            }
        })
    }


    private fun observableEarthQuakes() {
        mEarthQuakeModel.earthQuakes().observe(this, Observer<MutableList<Earthquake>> { earthquakes ->
            mEarthQuakes.clear()
            mEarthQuakes.addAll(earthquakes!!)
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun observableError() {
        mEarthQuakeModel.getError().observe(this, Observer<Throwable> { t -> processError(t!!) })
    }

    override fun onItemClick(earthquake: Earthquake?) {
        earthquake.let {
            startActivity(DetailActivity.startEarthQuakeActivity(this, earthquake!!))
        }
    }

    private fun processError(error: Throwable) {
        Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
        //deal with errors
    }
}
