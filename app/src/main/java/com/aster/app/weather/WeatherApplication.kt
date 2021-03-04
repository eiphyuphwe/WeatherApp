package com.aster.app.weather

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.aster.app.weather.di.component.ApplicationComponent
import com.aster.app.weather.di.component.DaggerApplicationComponent
import com.aster.app.weather.di.module.ApplicationModule

class WeatherApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}