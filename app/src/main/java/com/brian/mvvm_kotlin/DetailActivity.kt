package com.brian.mvvm_kotlin

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brian.mvvm_kotlin.data.Earthquake
import com.brian.mvvm_kotlin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var detailBinding: ActivityDetailBinding? = null

    private var mEarthquake : Earthquake? = null

    companion object {

        const val EXTRA_EARTH_QUAKE_ITEM = "extra_earth_quake_item"

        fun startEarthQuakeActivity(context: Context, earthquake: Earthquake) : Intent {
            return Intent(context, DetailActivity::class.java).putExtra(EXTRA_EARTH_QUAKE_ITEM, earthquake)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        mEarthquake = intent.getParcelableExtra(EXTRA_EARTH_QUAKE_ITEM)

        detailBinding?.earthquakeDetail = mEarthquake

    }
}
