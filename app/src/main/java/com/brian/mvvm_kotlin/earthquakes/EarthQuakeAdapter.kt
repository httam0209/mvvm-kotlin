package com.brian.mvvm_kotlin.earthquakes

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brian.mvvm_kotlin.R
import com.brian.mvvm_kotlin.callback.EarthQuakeItemCallback
import com.brian.mvvm_kotlin.data.Earthquake
import com.brian.mvvm_kotlin.databinding.EarthquakeItemBinding
import kotlinx.android.synthetic.main.earthquake_item.view.*

/**
 * @author Brian
 * @date: 6/11/18
 */


class EarthQuakeAdapter(context: Context, private var earths: MutableList<Earthquake>,
                        private var earthCallback: EarthQuakeItemCallback) :
        RecyclerView.Adapter<EarthQuakeAdapter.EarthViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthViewHolder {
        val itemBinding : EarthquakeItemBinding = DataBindingUtil.inflate(inflater, R.layout.earthquake_item, parent, false)
        itemBinding.callback = earthCallback
        return EarthViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return earths.size
    }

    override fun onBindViewHolder(holder: EarthViewHolder, position: Int) {
        holder.itemBinding.earthquake = earths[position]
        holder.itemBinding.executePendingBindings()
    }

    inner class EarthViewHolder(var itemBinding: EarthquakeItemBinding): RecyclerView.ViewHolder(itemBinding.root)
}