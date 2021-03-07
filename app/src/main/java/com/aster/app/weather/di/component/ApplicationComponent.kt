package com.aster.app.weather.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.aster.app.weather.WeatherApplication
import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.remote.NetworkService
import com.aster.app.weather.di.ApplicationContext
import com.aster.app.weather.di.module.ApplicationModule
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: WeatherApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}