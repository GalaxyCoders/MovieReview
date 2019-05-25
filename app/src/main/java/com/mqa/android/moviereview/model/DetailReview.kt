package com.mqa.android.moviereview.model

import com.google.gson.annotations.SerializedName

class DetailReview(
        @SerializedName("review")
        var review: String? = null,
        @SerializedName("formatted_date")
        var date: String? = null,
        @SerializedName("rating")
        var rating: String? = null
        )