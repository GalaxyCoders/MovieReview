package com.mqa.android.moviereview.module.fragment.MyReview

import android.util.Log
import com.google.gson.Gson
import com.mqa.android.kade.utils.CoroutineContextProvider
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.ReviewResponse
import com.mqa.android.moviereview.module.fragment.home.HomeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyReviewPresenter(private val view: MyReviewView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMyReviewList(id: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getMyReview(id)).await(),
                    ReviewResponse::class.java)
            Log.v("my review", "" + data.data)

            view.showMyReviewList(data.data)
            view.hideLoading()
        }
    }
}