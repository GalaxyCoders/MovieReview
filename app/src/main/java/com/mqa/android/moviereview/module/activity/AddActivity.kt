package com.mqa.android.moviereview.module.activity

import android.arch.lifecycle.Transformations.map
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.mqa.android.kade.utils.gone
import com.mqa.android.kade.utils.visible
import com.mqa.android.moviereview.R
import kotlinx.android.synthetic.main.activity_add.*
import com.google.gson.Gson
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.localhost
import com.mqa.android.moviereview.model.Movie2
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.RatingBar
import com.mqa.android.moviereview.api.APIService
import com.mqa.android.moviereview.api.ApiUtils
import com.mqa.android.moviereview.model.Movie
import com.pixplicity.easyprefs.library.Prefs
import khttp.post
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal


class AddActivity : AppCompatActivity(), AddView, RatingBar.OnRatingBarChangeListener {

    val userID: String = Prefs.getString("userId", "1")

    var movie: Int = 0
    var items: ArrayList<String?> = ArrayList()
    private var reviews: MutableList<Movie2> = mutableListOf()
    private var list_id: ArrayList<String?> = ArrayList()
    private lateinit var presenter: AddPresenter
    var quantity: Float = 0f
    val separated: ArrayList<String> = ArrayList()
    var title: String? = ""
    var id: String? = ""

    override fun showLoading() {
        pbarSelectMovie.visible()
    }

    override fun hideLoading() {
        pbarSelectMovie.gone()
    }

    override fun showMovieList(data: MutableList<Movie2>) {
//        movies = data[0]
        reviews.clear()
        reviews.addAll(data)
        reviews.map {
            items.add(it.title)
            list_id.add(it.movieId)
        }
//            id = it.movieId
//            it.title

        Log.v("id", "" + id)
        Log.v("title", "" + items)
        searchSpn.adapter = ArrayAdapter<String>(this, R.layout.spinner_item, items)
        searchSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                items.add("Choose movie")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                movie = p0?.selectedItemPosition!!
                Log.d("TAG", movie.toString())
                id = list_id[movie]
            }
        }


        Log.v("isi spinner 1", "" + movie)
        Log.v("id2", "" + id)
        Log.v("kualitas", "" + quantity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        presenter = AddPresenter(this, ApiRepository(), Gson())
        presenter.getMovieData()

        Log.v("isi spinner", "" + movie)
        addRettingTV.onRatingBarChangeListener = this

        addRattingBtn.onClick {
            quantity += 0.5f
            if (quantity >= 5.1f) {
                toast("maximum")
                quantity = 5.0f
                return@onClick
            }
            totalRatting(quantity)
        }

        lessRattingBtn.onClick {
            quantity -= 0.5f
            if (quantity <= 0.9f) {
                toast("minimum")
                quantity = 1.0f
                return@onClick
            }
            totalRatting(quantity)
        }

//        addRettingTV.setOnFocusChangeListener { view, hasFocus ->
//            if (hasFocus) {
//                if (addRettingTV.text.toString().trim().length < 5) {
//                    toast("lebih")
//                } else if (addRettingTV.text.toString().trim().length > 1) {
//                    toast("kurang")
//                }
//            }
//        }


    }

    private fun totalRatting(total: Float) {
        addRettingTV.rating = total
    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
        quantity = p1
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                val dialog = progressDialog(message = "Please wait a bit…", title = "Sending data")
                dialog.isIndeterminate = false
                dialog.show()
                Log.v("isi spinner 1", "" + movie)
                Log.v("id2", "" + id)
                Log.v("kualitas", "" + quantity)
                Log.v("review", "" + addReviewET.text)

                if (addReviewET.text.isEmpty()) {
                    addReviewET.error = "Review harus diisi"
                    dialog.dismiss()
                } else {

                    //Variable declaration
                    val mAPIService: APIService?

                    //After oncreate
                    mAPIService = ApiUtils.apiService

                    //Some Button click
                    mAPIService.addReviewPost(id, userID, quantity.toString(), addReviewET.text.toString()).enqueue(object : Callback<Movie> {

                        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                            Log.i("", "post submitted to API." + response.body()!!)

                            if (response.isSuccessful) {

                                Log.i("", "post registration to API" + response.body()!!.toString())
                                Log.i("", "post status to API" + response.code())
                                Log.i("", "post msg to API" + response.message())
                                toast("Berhasil membuat review")
                                startActivity<MainActivity>()

                            }
                        }

                        override fun onFailure(call: Call<Movie>, t: Throwable) {
                            val parentLayout = findViewById<View>(android.R.id.content)
                            parentLayout.snackbar("Masukkan data dengan benar")
                            dialog.dismiss()
                        }
                    })
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

}

