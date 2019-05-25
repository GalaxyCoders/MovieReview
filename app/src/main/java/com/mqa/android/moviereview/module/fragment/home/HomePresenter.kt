package com.mqa.android.moviereview.module.fragment.home

import android.util.Log
import com.google.gson.Gson
import com.mqa.android.kade.utils.CoroutineContextProvider
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.ReviewResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePresenter (private val view: HomeView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getReviewList(genre: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getReview(genre)).await(),
                    ReviewResponse::class.java)
            Log.v("ini dia",""+data.data)

            view.showReviewList(data.data)
            view.hideLoading()
        }
    }

    fun getReviewNull() {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getReviewnull()).await(),
                    ReviewResponse::class.java)
            Log.v("data",""+data.data)

            view.showReviewList(data.data)
            view.hideLoading()
        }
    }

    fun getSearchReviewList(review: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getSearchReview(review)).await(),
                    ReviewResponse::class.java)
            Log.v("data",""+data.data)

            view.showReviewList(data.data)
            view.hideLoading()
        }
    }
}