package com.mqa.android.moviereview.model

import com.google.gson.annotations.SerializedName

data class Review(
        @SerializedName("id_review")
        var reviewId: String? = null,
        @SerializedName("id_movie")
        var movieId: String? = null,
        @SerializedName("id_user")
        var userId: String? = null,
        @SerializedName("poster")
        var poster: String? = null,
        @SerializedName("rating")
        var rating: String? = null,
        @SerializedName("review")
        var review: String? = null,
        @SerializedName("formatted_date")
        var date: String? = null,
        @SerializedName("cover")
        var cover: String? = null,
        @SerializedName("description")
        var synopsis: String? = null,
        @SerializedName("trailer")
        var video: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("username")
        var name: String? = null
)