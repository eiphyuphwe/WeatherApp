package com.aster.app.weather.ui.home

import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.repository.WeatherForecastRepository

import com.aster.app.weather.ui.base.BaseViewModel
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        private val weatherForecastRepository: WeatherForecastRepository
       ):BaseViewModel(schedulerProvider,compositeDisposable,networkHelper)
{

    private val listItemList: ArrayList<ListItem> = ArrayList()
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val weather: MutableLiveData<Resource<List<ListItem>>> = MutableLiveData()

    fun onWeatherForecastLoading(city:String?)
    {
        compositeDisposable.addAll(
            weatherForecastRepository.fetchWeatherForecast(city)
                .subscribeOn(schedulerProvider.io())
                .subscribe({

                     print(it.size)
                    print(it.get(0).toString())

                },{

                })
        )
    }

    override fun onCreate() {

        onWeatherForecastLoading("Singapore")
    }


}