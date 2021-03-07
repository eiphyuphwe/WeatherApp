package com.aster.app.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.aster.app.weather.data.model.Wind

@Entity(tableName = "Wind")
data class WindEntity(
    @ColumnInfo(name = "deg")
    val deg: Double?,
    @ColumnInfo(name = "speed")
    val speed: Double?
) {
    @Ignore
    constructor(wind: Wind?) : this(
        deg = wind?.deg,
        speed = wind?.speed
    )
}
