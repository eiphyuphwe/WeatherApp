package com.aster.app.weather.utils

import androidx.room.TypeConverter
import com.aster.app.weather.data.model.WeatherItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataConverter {

    val gson = Gson()
    @TypeConverter
    @JvmStatic
    fun convertJsonStringToWeatherList(data:String?):List<WeatherItem>?
    {
        // List
        val listWeatherItemType = object : TypeToken<List<WeatherItem>>() {}.type
        var weatherItemList : List<WeatherItem> = gson.fromJson(data, listWeatherItemType)
        return weatherItemList
    }

    @TypeConverter
    @JvmStatic
    fun convertWeatherListToJsonString(weatherItemList:List<WeatherItem>) : String?
    {
            val jsonString = gson.toJson(weatherItemList)
         return jsonString
    }
}