package com.aster.app.weather.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostLikeRequest(

    @Expose
    @SerializedName("postId")
    val postId : String
)
