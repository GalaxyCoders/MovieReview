package com.mqa.android.moviereview.module.fragment.MyReview

import com.mqa.android.moviereview.model.Review

interface MyReviewView {
    fun showLoading()
    fun hideLoading()
    fun showMyReviewList(data: List<Review>)
}