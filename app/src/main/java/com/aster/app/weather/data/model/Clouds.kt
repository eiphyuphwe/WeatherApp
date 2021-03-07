package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    @Expose val all: Int?
)
