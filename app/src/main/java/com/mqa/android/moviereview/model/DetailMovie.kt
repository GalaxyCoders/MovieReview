package com.mqa.android.moviereview.model

import com.google.gson.annotations.SerializedName

class DetailMovie(
    @SerializedName("id")
    var movieId: String? = null,
    @SerializedName("description")
    var synopsis: String? = null,
    @SerializedName("release_date")
    var release: String? = null,
    @SerializedName("poster")
    var poster: String? = null,
    @SerializedName("genre")
    var genre: String? = null,
    @SerializedName("title")
    var title: String? = null
)