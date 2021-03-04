package com.aster.app.weather.domain.usecases

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.data.local.prefs.WeatherPreferenceDataStore
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.utils.TimestampCalculation
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class WeatherForecastUsecase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val weatherPreferenceDataStore: WeatherPreferenceDataStore
) : Usecase() {

    private val _weatherForecastLiveData = MutableLiveData<Resource<List<ListItem>>>()
    private val _weatherForecastLiveDataMap = MutableLiveData<Resource<Map<String,List<ListItem>>>>()

    var timeStamp : Long? = null
    val weatherForecastLiveData: LiveData<Resource<List<ListItem>>>
        get() = _weatherForecastLiveData

    val weatherForecastLiveDataMap:LiveData<Resource<Map<String,List<ListItem>>>>
        get() =_weatherForecastLiveDataMap

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getMapOfListItemBasedOnDate(list:List<ListItem>)
    {
       val listItemMap:Map<String,List<ListItem>>? = list?.groupBy {
           val sourceSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
           val requiredSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
           val reqFormattedDate1 = requiredSdf.format(sourceSdf.parse(it.date))
           reqFormattedDate1
        }

        Log.d(TAG,"MAP ="+listItemMap.toString());
        listItemMap?.keys?.forEach {
            Log.d (TAG,"Key ="+it)
         //   Log.d (TAG,"value ="+listItemMap.get(it))
            Log.d (TAG,"value Size="+listItemMap.get(it)?.size)
        }
        _weatherForecastLiveDataMap.postValue(Resource.success(listItemMap))
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun isDifferentDate(dateStr1:String?, dateStr2: String?):Boolean
    {
        var isDifferentDate = false
        try {
            /*val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val date1: Date = sdf.parse("2018-08-30")
            val date2: Date = sdf.parse("2018-08-28")*/

            try {
                val sourceSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val requiredSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val reqFormattedDate1 = requiredSdf.format(sourceSdf.parse(dateStr1))
                val reqFormattedDate2 = requiredSdf.format(sourceSdf.parse(dateStr2))
                val date1 = LocalDate.parse(reqFormattedDate1, DateTimeFormatter.ISO_DATE)
                val date2 =  LocalDate.parse(reqFormattedDate1, DateTimeFormatter.ISO_DATE)
                if(date1.compareTo(date2)!=0)
                {
                    isDifferentDate = true
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return isDifferentDate

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun invoke(city: Input.Single<String>) {

        // Get the timestamp from SharedPref
        // If not empty and within 3 hours, call the database
        // If empty or over 3 hours, call the network, save to database,
        timeStamp =  weatherPreferenceDataStore.getTimeStamp()
        timeStamp?.let {
            if(!TimestampCalculation.isTimestampStale(it))
            {
                //call the db
                val liveforecastEnity = weatherForecastRepository.getAllWeatherForecastFromDb()
                val size = liveforecastEnity.value?.list?.size
                Log.d(TAG, "ForecastEntity = " + liveforecastEnity.value?.id + " , size =" + size)

                liveforecastEnity.value?.let {

                    Log.d(TAG, "ForecastEntity = " + it.list?.size)
                    _weatherForecastLiveData.postValue(Resource.success(it.list))
                }

               /* Transformations.map(liveforecastEnity)
                {
                    it?.let {

                        Log.d(TAG,"ForecastEntity = "+ it.list?.size)
                        _weatherForecastLiveData.postValue(Resource.success(it.list))
                    }
                }*/

            }
            else{
                callWeatherForcastNetwork(city = city.data)
            }
        } ?: callWeatherForcastNetwork(city = city.data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun callWeatherForcastNetwork(city: String)
    {
        Log.d(TAG, "Network call")
        compositeDisposable.addAll(
            weatherForecastRepository.fetchWeatherForecast(city)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    Log.d(TAG,"Size ===="+it.size)
                    Log.d(TAG,"List<ListItem>====="+it)
                    it?.let {
                        val output: Resource<List<ListItem>> = Resource.success(it)

                        // Save to Db and update timestamp in SharedPref

                        weatherForecastRepository.insertWeatherForecastToDb(it)
                        Log.d(TAG,"before filterValidation")
                        getMapOfListItemBasedOnDate(it)
                        Log.d(TAG,"after filterValidation")

                        weatherPreferenceDataStore.setTimeStamp(TimestampCalculation.generateTimestamp())
                        _weatherForecastLiveData.postValue(output)
                    }
                }, {
                    it?.let {
                        it.printStackTrace();
                    }
                    _weatherForecastLiveData.postValue(Resource.error())
                })
        )
    }


    companion object {
        val TAG: String = WeatherForecastUsecase::class.toString()
    }


}