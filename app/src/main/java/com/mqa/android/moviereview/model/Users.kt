package com.mqa.android.moviereview.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Users {
    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("password")
    @Expose
    private var password: String? = null

    @SerializedName("id")
    @Expose
     var userId: Int? = null

}