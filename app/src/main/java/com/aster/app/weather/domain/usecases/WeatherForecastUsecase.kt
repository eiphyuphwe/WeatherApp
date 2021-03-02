package com.aster.app.weather.domain.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aster.app.weather.data.local.prefs.WeatherPreferenceDataStore
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.utils.TimestampCalculation
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherForecastUsecase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val weatherPreferenceDataStore: WeatherPreferenceDataStore
) : Usecase() {

    private val _weatherForecastLiveData = MutableLiveData<Resource<List<ListItem>>>()
    var timeStamp : Long? = null
    val weatherForecastLiveData: LiveData<Resource<List<ListItem>>>
        get() = _weatherForecastLiveData

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
                Log.d(TAG,"ForecastEntity = "+liveforecastEnity.value?.id+" , size ="+size)

                liveforecastEnity.value?.let {

                    Log.d(TAG,"ForecastEntity = "+ it.list?.size)
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

    fun callWeatherForcastNetwork(city:String)
    {
        Log.d(TAG, "Network call")
        compositeDisposable.addAll(
            weatherForecastRepository.fetchWeatherForecast(city)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    print(it.size)
                    print(it.get(0).toString())
                    it?.let {
                        val output: Resource<List<ListItem>> = Resource.success(it)

                        // Save to Db and update timestamp in SharedPref

                        weatherForecastRepository.insertWeatherForecastToDb(it)
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