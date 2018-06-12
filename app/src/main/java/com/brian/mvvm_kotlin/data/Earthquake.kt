package com.brian.mvvm_kotlin.data

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Brian
 * @date: 6/11/18
 */


data class Earthquake(
        var magnitude: Double? = null,
        var place: String? = null,
        var time: String? = null,
        var url: String? = null,
        var detail: String? = null,
        var status: String? = null,
        var type: String? = null,
        var title: String? = null

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(magnitude)
        writeString(place)
        writeString(time)
        writeString(url)
        writeString(detail)
        writeString(status)
        writeString(type)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Earthquake> = object : Parcelable.Creator<Earthquake> {
            override fun createFromParcel(source: Parcel): Earthquake = Earthquake(source)
            override fun newArray(size: Int): Array<Earthquake?> = arrayOfNulls(size)
        }
    }
}