package com.mqa.android.moviereview.module.activity

import android.util.Log
import com.google.gson.Gson
import com.mqa.android.kade.utils.CoroutineContextProvider
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.Movie2Response
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPresenter (private val view: AddView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMovieData() {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(localhost.getMovie()).await(),
                    Movie2Response::class.java)

            Log.v("isi sebelum",""+data.data)
            view.showMovieList(data.data)
            view.hideLoading()
        }
    }
}