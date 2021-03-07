package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherItem(

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("main")
    @Expose
    val main: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon")
    @Expose
    val icon: String
)
