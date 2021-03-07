package com.aster.app.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.aster.app.weather.data.model.Sys

@Entity(tableName = "Sys")
data class SysEntity(
    @ColumnInfo(name = "pod")
    val pod: String?
) {
    @Ignore
    constructor(sys: Sys?) : this(
        pod = sys?.pod

    )
}