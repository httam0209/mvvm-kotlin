package com.brian.mvvm_kotlin.data

/**
 * @author Brian
 * @date: 6/11/18
 */


data class EarthQuakeListResponse(

        var type: String? = null,
        var metadata: Metadata? = null,
        var features: MutableList<Feature>? = null,
        var bbox: MutableList<Double>? = null

) {


    inner class Metadata(
            var generated: String? = null,
            var url: String? = null,
            var title: String? = null,
            var status: String? = null,
            var api: String? = null,
            var count: Int? = null)

    inner class Feature(
            var type: String? = null,
            var properties: Properties? = null,
            var geometry: Geometry? = null,
            var id: String? = null) {


        inner class Properties(
                var mag: Double? = null,
                var place: String? = null,
                var time: String? = null,
                var url: String? = null,
                var detail: String? = null,
                var alert: String? = null,
                var status: String? = null,
                var tsunami: String? = null,
                var code: String? = null,
                var ids: String? = null,
                var type: String? = null,
                var title: String? = null)

        inner class Geometry(
                var type: String? = null,
                var coordinates: MutableList<Double>? = null
        )

    }
}