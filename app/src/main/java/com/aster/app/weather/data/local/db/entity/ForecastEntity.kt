package com.aster.app.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.aster.app.weather.data.model.ListItem


@Entity(tableName = "Forecast")
data class ForecastEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,


    @ColumnInfo(name = "list")
    var list: List<ListItem>?
) {

    @Ignore
    constructor(list: List<ListItem>) : this(
        id = 0,
        list = list
    )
}
