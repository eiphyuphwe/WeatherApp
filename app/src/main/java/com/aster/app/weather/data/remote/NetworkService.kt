package com.aster.app.weather.data.remote

import com.aster.app.weather.data.remote.request.DummyRequest
import com.aster.app.weather.data.remote.request.LoginRequest
import com.aster.app.weather.data.remote.request.PostLikeRequest
import com.aster.app.weather.data.remote.request.SignUpRequest
import com.aster.app.weather.data.remote.response.DummyResponse
import com.aster.app.weather.data.remote.response.GeneralResponse
import com.aster.app.weather.data.remote.response.LoginResponse
import com.aster.app.weather.data.remote.response.SignUpResponse
import com.aster.app.weather.data.remote.response.PostResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoints.HOME_POSTS_LIST)
    fun doHomePostListCall(
        @Query("firstPostId") firstPostId: String?,
        @Query("lastPostId") lastPostId: String?,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PostResponse>


}