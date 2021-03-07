package com.aster.app.weather.domain.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.aster.app.weather.data.local.prefs.WeatherPreference
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.utils.TimestampCalculation
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/*
*  WeatherForecastUsecase is business domain for clean architecture , that will communicate with repository for business logic
* */
class WeatherForecastUsecase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val weatherPreference: WeatherPreference,
    private val networkHelper: NetworkHelper
) : Usecase() {

    var timeStamp: Long? = null
    private val _weatherForecastLiveDataDB = weatherForecastRepository.getAllWeatherForecastFromDb()
    private val _weatherForecastLiveDataMap =
        MediatorLiveData<Resource<Map<String, List<ListItem>>>>()
    val weatherForecastLiveDataMap: LiveData<Resource<Map<String, List<ListItem>>>>
        get() = _weatherForecastLiveDataMap


    fun invoke(city: Input.Single<String>) {

        // Get the timestamp from SharedPref
        timeStamp = weatherPreference.getTimeStamp()
        timeStamp?.let {
            // If not empty and within 3 hours, call the database
            if (!TimestampCalculation.isTimestampStale(it)) {
                //call the db
                callWeatherForcastDB(city = city.data)
            } else {
                // If empty or over 3 hours, call the network, save to database,
                if(networkHelper.isNetworkConnected()){
                    callWeatherForcastNetwork(city = city.data)
                }
                else{
                    _weatherForecastLiveDataMap.value = Resource.error(null)

                }

            }
        }
            ?: callWeatherForcastNetwork(city = city.data) // If empty or over 3 hours, call the network, save to database,

    }

    private fun callWeatherForcastDB(city: String) {

        _weatherForecastLiveDataMap.addSource(_weatherForecastLiveDataDB) {
            _weatherForecastLiveDataMap.removeSource(_weatherForecastLiveDataDB)
            Log.d(TAG, "outside filterValidation DB")
            it.list?.let { listOfListItem ->
                Log.d(TAG, "inside filterValidation DB")
                _weatherForecastLiveDataMap.value = getMapOfListItemBasedOnDate(listOfListItem)
            }
        }
    }

    //group by the weather forecast listitem list, by date
    private fun getMapOfListItemBasedOnDate(list: List<ListItem>): Resource<Map<String, List<ListItem>>> {
        val listItemMap: Map<String, List<ListItem>>? = list?.groupBy {
            val sourceSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val requiredSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val reqFormattedDate1 = requiredSdf.format(sourceSdf.parse(it.date))
            reqFormattedDate1
        }
        return Resource.success(listItemMap)
    }

    // fetch the data from netwwork
    private fun callWeatherForcastNetwork(city: String) {
        Log.d(TAG, "Network call")
        compositeDisposable.addAll(
            weatherForecastRepository.fetchWeatherForecast(city)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    it?.let {
                        handleNetworkSuccess(it, city)
                    }
                }, {
                    it?.let {
                        it.printStackTrace();
                    }
                    _weatherForecastLiveDataMap.postValue(Resource.error())
                })
        )
    }

    /*
    * if fecth the data from network is success
    *  save the dat to db
    *  fetch the data  from database
    * */

    private fun handleNetworkSuccess(list: List<ListItem>, city: String) {
        Completable
            .fromCallable {
                weatherForecastRepository.insertWeatherForecastToDb(list)
                weatherPreference.setTimeStamp(TimestampCalculation.generateTimestamp())
            }
            .subscribeOn(schedulerProvider.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {
                    // Save to Db and update timestamp in SharedPref
                    callWeatherForcastDB(city = city)
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

            })


    }


    companion object {
        val TAG: String = WeatherForecastUsecase::class.toString()
    }


}