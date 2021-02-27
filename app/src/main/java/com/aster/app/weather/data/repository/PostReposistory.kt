package com.aster.app.weather.data.repository

import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.model.Post
import com.aster.app.weather.data.model.User
import com.aster.app.weather.data.remote.NetworkService
import com.aster.app.weather.data.remote.request.PostLikeRequest
import io.reactivex.Single
import javax.inject.Inject

class PostReposistory @Inject constructor(private val networkService: NetworkService,
                                          private val databaeService: DatabaseService)
{

    fun fetchHomePosts(firstPostId:String?,lastPostId:String?,user: User) : Single<List<Post>>
    {
        return networkService.doHomePostListCall(firstPostId,lastPostId,user.id,user.accessToken)
            .map { it.data }
    }


}