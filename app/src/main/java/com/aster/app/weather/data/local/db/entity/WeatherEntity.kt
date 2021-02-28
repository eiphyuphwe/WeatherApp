package com.aster.app.weather.data.local.db.entity

import android.graphics.Color
import androidx.room.*
import com.aster.app.weather.data.model.WeatherItem
import com.aster.app.weather.data.model.ListItem
import java.text.SimpleDateFormat
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.util.*

@Entity(tableName = "CurrentWeather")
data class WeatherEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "visibility")
        var visibility: Int?,
        @Embedded
        var main: MainEntity?,
        @ColumnInfo(name = "weather")
        val weather: List<WeatherItem?>? = null,
        @Embedded
        var clouds: CloudsEntity?,
        @Embedded
        var wind : WindEntity?,
        @ColumnInfo(name = "pop")
        var pop: Double?,
        @ColumnInfo(name = "dt")
        var dt: Long?,
        @Embedded
        var rain:RainEntity?,
        @Embedded
        var sys: SysEntity,
        @ColumnInfo(name = "dt_txt")
        var dt_txt:String?

)  {
    @Ignore
    constructor(currentWeather: ListItem) : this(
            visibility = currentWeather.visibility,
            main = MainEntity(currentWeather.main),
            clouds = CloudsEntity(currentWeather.clouds),
            dt = currentWeather.dt?.toLong(),
            weather = currentWeather.weatherList,
            id = 0,
            wind = WindEntity(currentWeather.wind),
            rain = RainEntity(currentWeather.rain),
            sys = SysEntity(currentWeather.sys),
            pop= currentWeather?.pop,
            dt_txt = currentWeather.date
    )

    fun getCurrentWeather(): WeatherItem? {
        return weather?.first()
    }

    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                    formattedDate.substringAfterLast("/").toInt(),
                    formattedDate.substringAfter("/").take(2).toInt(),
                    formattedDate.substringBefore("/").toInt()
            )
                    .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getColor(): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#28E0AE")
            DayOfWeek.TUESDAY -> Color.parseColor("#FF0090")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#FFAE00")
            DayOfWeek.THURSDAY -> Color.parseColor("#0090FF")
            DayOfWeek.FRIDAY -> Color.parseColor("#DC0000")
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF")
            DayOfWeek.SUNDAY -> Color.parseColor("#3D28E0")
            else -> Color.parseColor("#28E0AE")
        }
    }
}

