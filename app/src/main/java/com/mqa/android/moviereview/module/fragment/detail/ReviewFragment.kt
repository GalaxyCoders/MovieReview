package com.mqa.android.moviereview.module.fragment.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mqa.android.kade.utils.gone
import com.mqa.android.kade.utils.visible

import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.model.DetailReview
import kotlinx.android.synthetic.main.fragment_review.*


class ReviewFragment : Fragment(), ReviewView {
    private var idReview: String? = ""
    private var idMovie: String? = ""
    private var name: String? = ""
    private lateinit var mReview: DetailReview
    private lateinit var presenter: ReviewPresenter

    override fun showLoading() {
       pBarReview?.visible()
    }

    override fun hideLoading() {
        pBarReview?.gone()
    }

    override fun showReviewData(data: List<DetailReview>) {
        mReview=data[0]
        bindVIew()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = activity?.intent
        idReview = intent?.getStringExtra("id")
        name = intent?.getStringExtra("username")

        presenter = ReviewPresenter(this, ApiRepository(), Gson())
        presenter.getReviewData(idReview)
    }

    private fun bindVIew() {
        nameReviewTV.text = name
        ratingDetailTV.text = mReview.rating
        tanggalReviewTV.text = mReview.date
        reviewDetail2TV.text = mReview.review
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }


}
