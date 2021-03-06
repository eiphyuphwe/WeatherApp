package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListItem(
    @Expose
    @SerializedName("dt")
    val dt: Long,

    @Expose
    @SerializedName("main")
    val main: Main,

    @Expose
    @SerializedName("weather")
    val weatherList: List<WeatherItem>,

    @Expose
    @SerializedName("clouds")
    val clouds: Clouds,

    @Expose
    @SerializedName("wind")
    val wind: Wind,

    @Expose
    @SerializedName("rain")
    val rain: Rain,
    @Expose
    @SerializedName("sys")
    val sys: Sys,


    @Expose
    @SerializedName("visibility")
    val visibility: Int,

    @Expose
    @SerializedName("pop")
    val pop: Double?,

    @Expose
    @SerializedName("dt_txt")
    val date: String

)
