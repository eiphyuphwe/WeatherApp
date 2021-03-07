package com.aster.app.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.aster.app.weather.data.model.Clouds


@Entity(tableName = "Clouds")
data class CloudsEntity(
    @ColumnInfo(name = "all")
    var all: Int
) {
    @Ignore
    constructor(clouds: Clouds?) : this(
        all = clouds?.all ?: 0
    )
}
