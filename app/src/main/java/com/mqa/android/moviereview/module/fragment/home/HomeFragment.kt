package com.mqa.android.moviereview.module.fragment.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.google.gson.Gson
import com.mqa.android.kade.utils.gone
import com.mqa.android.kade.utils.visible
import com.mqa.android.moviereview.adapter.ReviewAdapter
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.model.Review
import com.mqa.android.moviereview.module.activity.DetailReviewActivity
import com.mqa.android.moviereview.module.activity.LoginActivity
import com.mqa.android.moviereview.module.activity.MainActivity
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.support.v4.startActivity




@Suppress("DEPRECATION")
class HomeFragment : Fragment(), HomeView {
    // TODO: Rename and change types of parameters
    private lateinit var genre: String
    private var reviews: MutableList<Review> = mutableListOf()
    private lateinit var presenter: HomePresenter
    private lateinit var adapter: ReviewAdapter

    override fun showLoading() {
        progressReview.visible()
    }

    override fun hideLoading() {
        progressReview.gone()
    }

    override fun showReviewList(data: List<Review>) {
        reviews.clear()
        reviews.addAll(data)
//        else errorMessage("data tidak ada")
//        if (reviews.size == 0) errorMessage("data tidak ada")
        adapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        val spinnerItems = resources.getStringArray(com.mqa.android.moviereview.R.array.genre)
        val spinnerAdapter = ArrayAdapter(requireContext(),
                com.mqa.android.moviereview.R.layout.spinner_item,
                spinnerItems)
        genreSpn?.adapter = spinnerAdapter

        adapter = ReviewAdapter(requireContext(), reviews) {
            startActivity<DetailReviewActivity>(
                    "id" to it.reviewId,
                    "movieId" to it.movieId,
                    "videoImage" to it.cover,
                    "video" to it.video,
                    "username" to it.name
            )
        }
        genreRV.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = HomePresenter(this, request, gson)
        genreSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                genre = genreSpn.selectedItem.toString()
                print(genre)
                if (genre == "All Genre")
                    presenter.getReviewNull()
                else
                    presenter.getReviewList(genre)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity)
                .setActionBarTitle("Home")

        val rootView = inflater.inflate(com.mqa.android.moviereview.R.layout.fragment_home, container, false)
        rootView.genreRV.layoutManager = LinearLayoutManager(activity)

        return rootView
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(com.mqa.android.moviereview.R.menu.menu_home, menu)
        val searchItem = menu?.findItem(com.mqa.android.moviereview.R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
//        searchView.backgroundColorResource = android.R.color.white
        searchView.queryHint = "Search Review Title"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchReviewList(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getSearchReviewList(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//
//        when (item?.itemId) {
//            R.id.logout -> {
//                Prefs.remove("userId")
//                startActivity<LoginActivity>()
//            }
//        }
//
//        return false
//    }

    companion object {
        fun homeInstance() =
                HomeFragment()
    }

}
