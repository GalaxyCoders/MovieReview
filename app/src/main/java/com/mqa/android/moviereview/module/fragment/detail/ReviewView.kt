package com.mqa.android.moviereview.module.fragment.detail

import com.mqa.android.moviereview.model.DetailReview

interface ReviewView {
    fun showLoading()
    fun hideLoading()
    fun showReviewData(data: List<DetailReview>)
}