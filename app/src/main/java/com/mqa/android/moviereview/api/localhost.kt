package com.mqa.android.moviereview.api

import com.mqa.android.moviereview.BuildConfig

object localhost {

    val BASE_URL = "http://10.4.139.105/moviereview/"

    fun getReview(genre: String?): String {
        return "$BASE_URL?action=get_review&genre=$genre"
    }

    fun getReviewnull(): String {
        return "$BASE_URL?action=getalldata"
    }

    fun getReviewDetail(id: String): String {
        return "$BASE_URL?action=getReviewDetail&id=$id"
    }

    fun getMovieDetail(id: String): String {
        return "$BASE_URL?action=getMovieDetail&id=$id"
    }

    fun getMyReview(id: String): String {
        return "$BASE_URL?action=get_myReview&id_user=$id"
    }

    fun getSearchReview(review: String?): String {
        return "$BASE_URL?action=search_review&title=$review"
    }

    fun deleteMyReview(id: String): String {
        return "$BASE_URL?action=delete_review&id_review=$id"
    }

    fun getMovie(): String {
        return "$BASE_URL?action=get_movie"
    }

    fun insertReview(): String {
        return "$BASE_URL?action=insert_review"
    }
}