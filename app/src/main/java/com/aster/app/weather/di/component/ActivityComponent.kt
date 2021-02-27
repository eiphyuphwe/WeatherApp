package com.aster.app.weather.di.component

import com.aster.app.weather.di.ActivityScope
import com.aster.app.weather.di.module.ActivityModule
import com.aster.app.weather.ui.main.MainActivity
import com.aster.app.weather.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity:MainActivity)
}