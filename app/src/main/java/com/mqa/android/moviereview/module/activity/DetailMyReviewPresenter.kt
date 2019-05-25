package com.mqa.android.moviereview.module.activity

import android.util.Log
import com.google.gson.Gson
import com.mqa.android.kade.utils.CoroutineContextProvider
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.Movie2Response
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMyReviewPresenter (private val view: DetailMyReviewView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun deleteReviewData(id: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.deleteMyReview(id)).await(),
                    Movie2Response::class.java)

            Log.v("isi sebelum",""+data.data)
            view.deleteReview()
            view.hideLoading()
        }
    }
}