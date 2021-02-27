package com.aster.app.weather.di.component

import com.aster.app.weather.di.ViewModelScope
import com.aster.app.weather.di.module.ViewHolderModule
import com.aster.app.weather.ui.home.homepost.PostItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: PostItemViewHolder)

}