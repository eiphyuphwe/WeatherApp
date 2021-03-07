package com.aster.app.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.aster.app.weather.data.model.Main


@Entity(tableName = "Main")
data class MainEntity(
        @ColumnInfo(name = "temp")
        var temp: Double?,
        @ColumnInfo(name = "feels_like")
        var feels_like: Double?,
        @ColumnInfo(name = "tempMin")
        var tempMin: Double?,
        @ColumnInfo(name = "grndLevel")
        var grndLevel: Double?,
        @ColumnInfo(name = "tempKf")
        var humidity: Int?,
        @ColumnInfo(name = "pressure")
        var pressure: Double?,
        @ColumnInfo(name = "seaLevel")
        var seaLevel: Double?,
        @ColumnInfo(name = "tempMax")
        var tempMax: Double?
) {

    @Ignore
    constructor(main: Main?) : this(
            temp = main?.temp,
            feels_like = main?.feelLike,
            tempMin = main?.tempMin,
            grndLevel = main?.grndLevel,
            humidity = main?.humidity,
            pressure = main?.pressure,
            seaLevel = main?.sealevel,
            tempMax = main?.tempMax
    )
}
