package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main(
    @Expose
    @SerializedName("temp") val temp: Double?,

    @Expose
    @SerializedName("feels_like")
    val feelLike: Double?,

    @SerializedName("pressure")
    @Expose
    val pressure: Double?,
    @SerializedName("sea_level")
    @Expose
    val sealevel: Double?,

    @SerializedName("humidity")
    @Expose
    val humidity: Int?,
    @SerializedName("temp_min")
    @Expose
    val tempMin: Double?,
    @SerializedName("temp_max")
    @Expose
    val tempMax: Double?,
    @SerializedName("grnd_level")
    @Expose
    val grndLevel: Double?

) {

}
