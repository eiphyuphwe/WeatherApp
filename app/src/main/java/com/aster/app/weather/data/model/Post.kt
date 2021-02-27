package com.aster.app.weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class Post(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("imgUrl")
    val imgUrl: String,

    @Expose
    @SerializedName("imgWidth")
    val imgWidth : Int?,

    @Expose
    @SerializedName("imgHeight")
    val imgHeight : Int?,

    @Expose
    @SerializedName("user")
    val createdUser : User,

    @Expose
    @SerializedName("likedBy")
    val likedBy: ArrayList<User>?,

    @Expose
    @SerializedName("createdAt")
    val createdAt : Date


) {
    data class User(
        @Expose
        @SerializedName("id")
        val userId: String,

        @Expose
        @SerializedName("name")
        val name: String,

        @Expose
        @SerializedName("profilePicUrl")
        val profilePicUrl: String?

    )
}