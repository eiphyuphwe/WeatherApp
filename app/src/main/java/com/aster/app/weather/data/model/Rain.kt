package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rain(
        @Expose
        @SerializedName("3h")
        val jsonMember3h:Double?
)
