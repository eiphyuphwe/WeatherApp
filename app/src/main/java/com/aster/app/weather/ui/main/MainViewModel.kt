package com.aster.app.weather.ui.main

import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.ui.base.BaseViewModel
import com.aster.app.weather.utils.common.Event
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(schedulerProvider:SchedulerProvider,compositeDisposable: CompositeDisposable,
networkHelper: NetworkHelper) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper){

    val profileNavigation = MutableLiveData<Event<Boolean>>()
    val homeNavigation = MutableLiveData<Event<Boolean>>()
    val photoNavigation = MutableLiveData<Event<Boolean>>()


    fun onProfileSelected()
    {
        profileNavigation.postValue(Event(true))
    }

    fun onHomeNavSelected()
    {
        homeNavigation.postValue(Event(true))
    }

    fun onPhotoNavSelected() = photoNavigation.postValue(Event(true))
    override fun onCreate() {

        homeNavigation.postValue(Event(true))
    }


}