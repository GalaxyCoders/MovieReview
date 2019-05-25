package com.mqa.android.moviereview.module.fragment.detail

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mqa.android.kade.utils.gone
import com.mqa.android.kade.utils.invisible
import com.mqa.android.kade.utils.visible

import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.model.DetailMovie
import com.mqa.android.moviereview.model.Review
import com.mqa.android.moviereview.module.fragment.home.HomeView
import kotlinx.android.synthetic.main.fragment_detail_movie.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailMovieFragment : Fragment(), MovieView {

    private var idReview: String? = ""
    private var idMovie: String? = ""
    private lateinit var mMovie: DetailMovie
    private lateinit var presenter: MoviePresenter

    override fun showLoading() {
        pBarMovie?.visible()
        movieLL?.invisible()
    }

    override fun hideLoading() {
        pBarMovie?.gone()
        movieLL?.visible()
    }

    override fun showReviewList(data: List<DetailMovie>) {
        mMovie = data[0]
        bindVIew()
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = activity?.intent
        idMovie = intent?.getStringExtra("movieId")

        presenter = MoviePresenter(this, ApiRepository(), Gson())
        presenter.getMovieData(idMovie)
    }

    private fun bindVIew() {
        titleDetailTV.text = mMovie.title
        genreDetailTV.text = mMovie.genre
        releaseDetailTV.text = mMovie.release
        reviewDetailTV.text = mMovie.synopsis
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun movieInstance(): DetailMovieFragment = DetailMovieFragment()
    }

}
