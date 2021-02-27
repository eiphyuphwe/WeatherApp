package com.aster.app.weather.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aster.app.weather.data.repository.PostReposistory
import com.aster.app.weather.data.repository.UserRepository
import com.aster.app.weather.ui.base.BaseFragment
import com.aster.app.weather.ui.home.HomeViewModel
import com.aster.app.weather.ui.home.homepost.PostAdapter
import com.aster.app.weather.utils.ViewModelProviderFactory
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)




    @Provides
    fun providesPostAdapter() = PostAdapter(fragment.lifecycle,ArrayList())

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        postRepository: PostReposistory
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                schedulerProvider, compositeDisposable, networkHelper, userRepository,
                postRepository, ArrayList(), PublishProcessor.create()
            )
        }).get(HomeViewModel::class.java)
}