package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys(
    @Expose
    @SerializedName("pod")
    val pod: String?
)
