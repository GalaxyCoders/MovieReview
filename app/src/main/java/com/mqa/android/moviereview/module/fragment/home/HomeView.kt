package com.mqa.android.moviereview.module.fragment.home

import com.mqa.android.moviereview.model.Review

interface HomeView {
    fun showLoading()
    fun hideLoading()
    fun showReviewList(data: List<Review>)
}