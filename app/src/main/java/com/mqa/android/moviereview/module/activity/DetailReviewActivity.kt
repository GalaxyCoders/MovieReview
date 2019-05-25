package com.mqa.android.moviereview.module.activity

import android.support.v7.app.AppCompatActivity

import android.support.v4.view.ViewPager
import android.os.Bundle

import com.mqa.android.moviereview.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_review.*
import android.content.Intent
import android.net.Uri
import com.mqa.android.moviereview.adapter.ViewPagerAdapter
import com.mqa.android.moviereview.module.fragment.detail.DetailMovieFragment
import com.mqa.android.moviereview.module.fragment.detail.ReviewFragment
import org.jetbrains.anko.sdk27.coroutines.onClick
import android.support.design.widget.CoordinatorLayout
import android.widget.FrameLayout
import android.app.Activity
import android.app.PendingIntent.getActivity


class DetailReviewActivity : AppCompatActivity() {

    private var idReview: String? = ""
    private var idMovie: String? = ""
    private var cover: String? = ""
    private var video: String? = ""
    private lateinit var viewPager: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_review)

        viewPager = com.mqa.android.moviereview.adapter.ViewPagerAdapter(supportFragmentManager)

        viewPagerAdapter(container2)
        tabs.setupWithViewPager(container2)

        val intent = intent
        idReview = intent.getStringExtra("id")
        idMovie = intent.getStringExtra("movieId")
        cover = intent.getStringExtra("videoImage")
        video = intent.getStringExtra("video")

        Picasso.get().load(cover).into(videoIV)

        videoIV.onClick {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(video)))
        }

    }

    private lateinit var mViewPager: ViewPager

    private fun viewPagerAdapter(pager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

//        val movie = DetailMovieFragment.movieInstance()
        adapter.addFragment(DetailMovieFragment(), "Movie Info")

//        val review = ReviewFragment.reviewInstance()
        adapter.addFragment(ReviewFragment(), "Review")

        pager.adapter = adapter
    }

}
