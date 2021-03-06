package com.aster.app.weather.ui.home.homepost

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.aster.app.weather.R
import com.aster.app.weather.data.model.Post
import com.aster.app.weather.di.component.ViewHolderComponent
import com.aster.app.weather.ui.base.BaseItemViewHolder



class PostItemViewHolder(parent: ViewGroup): BaseItemViewHolder<Post, PostItemViewModel>(R.layout.item_view_weather_time_snapshots,parent)
{
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {


    }

    override fun setupObservers() {
        super.setupObservers()


    }
}