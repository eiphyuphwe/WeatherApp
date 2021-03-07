package com.aster.app.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.aster.app.weather.data.model.Rain

@Entity(tableName = "Rain")
data class RainEntity(

    @ColumnInfo(name = "3h")
    var jsonMember3h: Double?
) {

    @Ignore
    constructor(rain: Rain?) : this(
        jsonMember3h = rain?.jsonMember3h

    )
}