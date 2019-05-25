package com.mqa.android.moviereview.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("id_movie")
    @Expose
    private var movieId: Int? = null
    @SerializedName("id_user")
    @Expose
    private var userId: Int? = null
    @SerializedName("id_review")
    @Expose
    private var reviewId: Int? = null
    @SerializedName("rating")
    @Expose
    private var ratting: Float? = null
    @SerializedName("review")
    @Expose
    private var review: String? = null
}