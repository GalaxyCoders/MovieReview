package com.mqa.android.moviereview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.model.Review
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_data.view.*
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class ReviewAdapter (private val context: Context, private val review: MutableList<Review>, private val listener: (Review) -> Unit)
    : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_data, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(review[position], listener)
    }

    override fun getItemCount(): Int = review.count()

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindItem(reviewsData: Review, listener: (Review) -> Unit) {
            itemView.rattingTV.text = reviewsData.rating
            itemView.nameTV.text = reviewsData.title
            itemView.usernameTV.text = reviewsData.name
            itemView.dateTV.text = reviewsData.date
            itemView.reviewTV.text = reviewsData.review
            Picasso.get().load(reviewsData.poster).into(itemView.posterIV)
            itemView.setOnClickListener {
                listener(reviewsData)
            }
        }

    }

}