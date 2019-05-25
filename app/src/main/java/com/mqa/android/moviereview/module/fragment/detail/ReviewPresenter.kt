package com.mqa.android.moviereview.module.fragment.detail

import android.util.Log
import com.google.gson.Gson
import com.mqa.android.kade.utils.CoroutineContextProvider
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.DetailReviewResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReviewPresenter (private val view: ReviewView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getReviewData(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getReviewDetail(id!!)).await(),
                    DetailReviewResponse::class.java)

            view.showReviewData(data.data)
            view.hideLoading()
        }
    }
}