package com.aster.app.weather.domain.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.domain.Usecase
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherForecastUsecase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider
) : Usecase() {

    private val _weatherForecastLiveData = MutableLiveData<Resource<List<ListItem>>>()

    val weatherForecastLiveData: LiveData<Resource<List<ListItem>>>
        get() = _weatherForecastLiveData

    fun invoke(city: Input.Single<String>) {

        //TODO aster
        // Get the timestamp from SharedPref
            // If not empty and within 3 hours, call the database
            // If empty or over 3 hours, call the network, save to database,

        Log.d(TAG, "Network call")
        compositeDisposable.addAll(
            weatherForecastRepository.fetchWeatherForecast(city = city.data)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    print(it.size)
                    print(it.get(0).toString())
                    it?.let {
                        val output: Resource<List<ListItem>> = Resource.success(it)
                        //TODO aster
                        // Save to Db and update timestamp in SharedPref
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