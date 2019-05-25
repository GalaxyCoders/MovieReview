package com.mqa.android.moviereview.module.fragment.MyReview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.google.gson.Gson
import com.mqa.android.kade.utils.gone
import com.mqa.android.kade.utils.visible

import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.adapter.ReviewAdapter
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.model.Review
import com.mqa.android.moviereview.module.activity.DetailMyReviewActivity
import com.mqa.android.moviereview.module.activity.LoginActivity
import com.mqa.android.moviereview.module.activity.MainActivity
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton


class MyReviewFragment : Fragment(), MyReviewView {

    val userID: String = Prefs.getString("userId", "1")
    private var reviews: MutableList<Review> = mutableListOf()
    private lateinit var presenter: MyReviewPresenter
    private lateinit var adapter: ReviewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        adapter = ReviewAdapter(requireContext(),reviews) {
            startActivity<DetailMyReviewActivity>(
                    "id" to it.reviewId,
                    "movieId" to it.movieId,
                    "movie" to it.title,
                    "review" to it.review,
                    "ratting" to it.rating,
                    "username" to it.name
            )
        }
        genreRV.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MyReviewPresenter(this, request, gson)
        presenter.getMyReviewList(userID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity)
                .setActionBarTitle("My Review")

        val rootView = inflater.inflate(R.layout.user_my_review_fragment, container, false)
        rootView.genreRV.layoutManager = LinearLayoutManager(activity)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_myreview, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_logout -> {
                alert ("Are you sure to logout?", "LogOut") {
                    yesButton {
                        Prefs.remove("userId")
                        startActivity<MainActivity>()
                    }
                    noButton {  }
                }.show()
            }else ->
            super.onOptionsItemSelected(item)
        }

        return false
    }

    companion object {
        fun myInstance() =
                MyReviewFragment()
    }

    override fun showLoading() {
        progressReview?.visible()
    }

    override fun hideLoading() {
        progressReview?.gone()
    }

    override fun showMyReviewList(data: List<Review>) {
        reviews.clear()
        reviews.addAll(data)
        adapter.notifyDataSetChanged()
    }

}