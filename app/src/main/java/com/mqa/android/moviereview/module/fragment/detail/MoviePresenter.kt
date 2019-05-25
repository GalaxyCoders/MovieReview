package com.mqa.android.moviereview.module.fragment.detail

import android.util.Log
import com.google.gson.Gson
import com.mqa.android.kade.utils.CoroutineContextProvider
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.DetailMovieResponse
import com.mqa.android.moviereview.model.ReviewResponse
import com.mqa.android.moviereview.module.fragment.home.HomeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviePresenter (private val view: MovieView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMovieData(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getMovieDetail(id!!)).await(),
                    DetailMovieResponse::class.java)
            Log.v("ini dia",""+data.data)

            view.showReviewList(data.data)
            view.hideLoading()
        }
    }
}