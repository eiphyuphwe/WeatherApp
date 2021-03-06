package com.aster.app.weather.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aster.app.weather.domain.usecases.WeatherForecastUsecase
import com.aster.app.weather.ui.base.BaseFragment
import com.aster.app.weather.ui.home.HomeViewModel
import com.aster.app.weather.utils.ViewModelProviderFactory
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)


    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        weatherForecastUsecase: WeatherForecastUsecase
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                schedulerProvider, compositeDisposable, networkHelper,
                weatherForecastUsecase
            )
        }).get(HomeViewModel::class.java)
}