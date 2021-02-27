package com.aster.app.weather.ui.home.homepost

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.aster.app.weather.data.model.Post
import com.aster.app.weather.ui.base.BaseAdapter

class PostAdapter(parentLifeCycle: Lifecycle, private val postList:ArrayList<Post>) :
    BaseAdapter<Post, PostItemViewHolder>(parentLifeCycle, postList){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder = PostItemViewHolder(parent)


}