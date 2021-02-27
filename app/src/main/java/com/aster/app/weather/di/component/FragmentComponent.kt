package com.aster.app.weather.di.component

import com.aster.app.weather.di.FragmentScope
import com.aster.app.weather.di.module.FragmentModule
import com.aster.app.weather.ui.home.HomeFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {



    fun inject(fragment: HomeFragment)
}