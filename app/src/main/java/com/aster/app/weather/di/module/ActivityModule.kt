package com.aster.app.weather.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aster.app.weather.data.repository.UserRepository
import com.aster.app.weather.ui.base.BaseActivity
import com.aster.app.weather.ui.main.MainViewModel
import com.aster.app.weather.ui.splash.SplashViewModel
import com.aster.app.weather.utils.ViewModelProviderFactory
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)


    @Provides
    fun providesMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper):MainViewModel = ViewModelProviders.of(
        activity,ViewModelProviderFactory(MainViewModel::class){
            MainViewModel(schedulerProvider,compositeDisposable,networkHelper)
        }).get(MainViewModel::class.java)


}