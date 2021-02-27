package com.aster.app.weather.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.aster.app.weather.data.model.Post

data class PostResponse(

    @Expose
    @SerializedName("statusCode")
    val statusCode : String,

    @Expose
    @SerializedName("status")
    val status : String,

    @Expose
    @SerializedName("message")
    val message : String,

    @Expose
    @SerializedName("data")
    val data : List<Post>
)
