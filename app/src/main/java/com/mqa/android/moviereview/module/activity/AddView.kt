package com.mqa.android.moviereview.module.activity

import com.mqa.android.moviereview.model.Movie2

interface AddView {
    fun showLoading()
    fun hideLoading()
    fun showMovieList(data: MutableList<Movie2>)
}