package com.aster.app.weather.data.local.db.entity

import android.os.Parcelable
import androidx.room.*
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.remote.response.ForecastResponse


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
