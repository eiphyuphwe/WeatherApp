package com.aster.app.weather.di.module

import androidx.lifecycle.LifecycleRegistry
import com.aster.app.weather.di.ViewModelScope
import com.aster.app.weather.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}