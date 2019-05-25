package com.mqa.android.moviereview.module.fragment.detail

import com.mqa.android.moviereview.model.DetailMovie


interface MovieView {
    fun showLoading()
    fun hideLoading()
    fun showReviewList(data: List<DetailMovie>)
}