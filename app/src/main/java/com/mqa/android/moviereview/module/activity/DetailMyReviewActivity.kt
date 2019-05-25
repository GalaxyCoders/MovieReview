package com.mqa.android.moviereview.module.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.mqa.android.kade.utils.gone
import com.mqa.android.kade.utils.visible
import com.mqa.android.moviereview.api.APIService
import com.mqa.android.moviereview.api.ApiRepository
import com.mqa.android.moviereview.api.ApiUtils
import com.mqa.android.moviereview.model.Movie
import com.mqa.android.moviereview.model.Movie2
import kotlinx.android.synthetic.main.activity_add.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import android.view.MotionEvent
import android.widget.RatingBar
import com.mqa.android.moviereview.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.user_detail_my_review.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onTouch


@Suppress("DEPRECATION")
class DetailMyReviewActivity : AppCompatActivity(), RatingBar.OnRatingBarChangeListener {

    val userID: String = Prefs.getString("userId", "1")

    private var idReview: String? = null
    private var idMovie: String? = String()
    private var review: String? = String()
    private var ratting: String? = null
    private var username: String? = String()
    var movie: Int = 0
    var items: ArrayList<String?> = ArrayList()
    private var movies: MutableList<Movie2> = mutableListOf()
    private var list_id: ArrayList<String?> = ArrayList()
    private lateinit var presenter: AddPresenter
    var quantity: Float? = null
    var title: String? = ""
    var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail_my_review)

//        presenter = AddPresenter(this, ApiRepository(), Gson())
//        presenter.getMovieData()

        val intent = intent
        idReview = intent.getStringExtra("id")
        idMovie = intent.getStringExtra("movieId")
        title = intent.getStringExtra("movie")
        review = intent.getStringExtra("review")
        ratting = intent.getStringExtra("ratting")
        username = intent.getStringExtra("username")
        textTitileTV.text = title

        addReview2ET.text = Editable.Factory.getInstance().newEditable(review)
        quantity = ratting?.toFloat()
        totalRatting(quantity!!)
        addRetting2TV.onRatingBarChangeListener = this

        addRatting2Btn.onClick {
            quantity = quantity?.plus(0.5f)
            if (quantity!! >= 5.1f) {
                toast("maximum")
                quantity = 5.0f
                return@onClick
            }
            totalRatting(quantity!!)
        }

        lessRatting2Btn.onClick {
            quantity = quantity?.minus(0.5f)
            if (quantity!! <= 0.9f) {
                toast("minimum")
                quantity = 1.0f
                return@onClick
            }
            totalRatting(quantity!!)
        }

        add2Btn.onClick {
            val dialog = progressDialog(message = "Please wait a bit…", title = "Sending data")
            dialog.isIndeterminate = false
            dialog.show()

            Log.v("isi spinner 1", "" + movie)
            Log.v("id2", "" + id)
            Log.v("kualitas", "" + quantity)
            Log.v("review", "" + addReview2ET.text)

            if (addReview2ET.text.isEmpty())
                addReview2ET.error = "Review harus diisi"

            //Variable declaration
            val mAPIService: APIService?
            mAPIService = ApiUtils.apiService

            //Some Button click
            mAPIService.updateReviewPost(idMovie, userID, idReview, quantity.toString(), addReview2ET.text.toString()).enqueue(object : Callback<Movie> {

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                    Log.i("", "post submitted to API." + response.body()!!)

                    if (response.isSuccessful) {

                        Log.i("", "post registration to API" + response.body()!!.toString())
                        Log.i("", "post status to API" + response.code())
                        Log.i("", "post msg to API" + response.message())
                        dialog.dismiss()
                        toast("Berhasil mengubah review")
                        val intent = Intent(this@DetailMyReviewActivity, MainActivity::class.java)
                        intent.putExtra("key", 1)
                        startActivity(intent)


                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    val parentLayout = findViewById<View>(android.R.id.content)
                    parentLayout.snackbar("Data tidak boleh kosong")
                    dialog.dismiss()
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.mqa.android.moviereview.R.menu.menu_detail_myreview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            com.mqa.android.moviereview.R.id.delete_review_item -> {
                alert("Are you sure to delete this review?", "Delete Review") {
                    yesButton {
                        val dialog = progressDialog(message = "Please wait a bit…", title = "Deleting data")
                        dialog.isIndeterminate = false
                        dialog.show()

                        //Variable declaration
                        val mAPIService: APIService?

                        //After oncreate
                        mAPIService = ApiUtils.apiService

                        //Some Button click
                        mAPIService.deleteReview(idReview).enqueue(object : Callback<Movie> {

                            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                                Log.i("", "get submitted to API." + response.body()!!)

                                if (response.isSuccessful) {

                                    Log.i("", "get registration to API" + response.body()!!.toString())
                                    Log.i("", "get status to API" + response.code())
                                    Log.i("", "get msg to API" + response.message())
                                    toast("Berhasil menghapus review")
                                    dialog.dismiss()
                                    startActivity<MainActivity>("key" to 1)

                                }
                            }

                            override fun onFailure(call: Call<Movie>, t: Throwable) {
                                val parentLayout = findViewById<View>(android.R.id.content)
                                parentLayout.snackbar("Gagal menghapus review")
                                dialog.dismiss()
                            }
                        })
                    }
                    noButton {}
                }.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun totalRatting(total: Float) {
        addRetting2TV.rating = total
    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
        quantity = p1
    }

//    override fun showLoading() {
//        pbarSelectMovie.gone()
//    }
//
//    override fun hideLoading() {
//        pbarSelectMovie.gone()
//    }
//
//    override fun showMovieList(data: MutableList<Movie2>) {
////        movies = data[0]
//        movies.clear()
//        movies.addAll(data)
//        items.add(title)
//        list_id.add(idMovie)
//        movies.map {
//            items.add(it.title)
//            list_id.add(it.movieId)
//        }
//
//        Log.v("id", "" + id)
//        Log.v("title", "" + items)
//        Log.d("list1", list_id.toString())
////        var isSpinnerTouched: Boolean  = false
////        searchSpn.adapter = ArrayAdapter<String>(this, com.mqa.android.moviereview.R.layout.spinner_item, items)
////        searchSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
////            override fun onNothingSelected(p0: AdapterView<*>?) {
////
////                id = idReview
////            }
////
////            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//////                list_id.remove(idMovie)
//////                items.remove(title)
////                movie = p0?.selectedItemPosition!!
////                Log.d("TAG", movie.toString())
////                Log.d("list", list_id.toString())
////                id = list_id[movie--]
////                Log.d("TAG", movie.toString())
////            }
////        }
////        searchSpn.onTouch { v, event ->
////            list_id.remove(idMovie)
////                items.remove(title)
////        }
//
//        Log.v("isi spinner 1", "" + movie)
//        Log.v("id2", "" + id)
//        Log.v("kualitas", "" + quantity)
//
//        add2Btn.onClick {
//            val dialog = progressDialog(message = "Please wait a bit…", title = "Sending data")
//            dialog.isIndeterminate = false
//            dialog.show()
//
//            Log.v("isi spinner 1", "" + movie)
//            Log.v("id2", "" + id)
//            Log.v("kualitas", "" + quantity)
//            Log.v("review", "" + addReview2ET.text)
//
//            if (addReview2ET.text.isEmpty())
//                addReview2ET.error = "Review harus diisi"
//
//            //Variable declaration
//            val mAPIService: APIService?
//            mAPIService = ApiUtils.apiService
//
//            //Some Button click
//            mAPIService.updateReviewPost(idMovie, userID, idReview, quantity.toString(), addReview2ET.text.toString()).enqueue(object : Callback<Movie> {
//
//                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
//
//                    Log.i("", "post submitted to API." + response.body()!!)
//
//                    if (response.isSuccessful) {
//
//                        Log.i("", "post registration to API" + response.body()!!.toString())
//                        Log.i("", "post status to API" + response.code())
//                        Log.i("", "post msg to API" + response.message())
//                        dialog.dismiss()
//                        toast("Berhasil mengubah review")
//                        val intent = Intent(this@DetailMyReviewActivity, MainActivity::class.java)
//                        intent.putExtra("key", 1)
//                        startActivity(intent)
//
//
//                    }
//                }
//
//                override fun onFailure(call: Call<Movie>, t: Throwable) {
//                    val parentLayout = findViewById<View>(android.R.id.content)
//                    parentLayout.snackbar("Data tidak boleh kosong")
//                    dialog.dismiss()
//                }
//            })
//        }
//
//    }

}

