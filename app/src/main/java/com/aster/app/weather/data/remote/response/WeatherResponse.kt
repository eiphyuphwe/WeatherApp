package com.aster.app.weather.data.remote.response

import com.aster.app.weather.data.model.WeatherPojo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResponse(

        @SerializedName("cod")
        @Expose
        val cod : String,

        @SerializedName("message")
        @Expose
        val message:Int,

        @SerializedName("cnt")
        @Expose
        val cnt : Int,

        @SerializedName("list")
        @Expose
        val list : List<WeatherPojo>
)
