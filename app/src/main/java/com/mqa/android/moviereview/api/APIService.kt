package com.mqa.android.moviereview.api

import com.mqa.android.moviereview.model.Movie
import com.mqa.android.moviereview.model.Users
import com.mqa.android.moviereview.model.UsersResponse
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET



interface APIService {
    @POST("?action=insert_review")
    @FormUrlEncoded
    fun addReviewPost(@Field("id_movie") movie_id: String?,
                         @Field("id_user") user_id: String,
                         @Field("rating") ratting: String,
                         @Field("review") review: String): Call<Movie>

    @POST("?action=update_review")
    @FormUrlEncoded
    fun updateReviewPost(@Field("id_movie") movie_id: String?,
                         @Field("id_user") user_id: String,
                         @Field("id_review") review_id: String?,
                         @Field("rating") ratting: String,
                         @Field("review") review: String): Call<Movie>

    @POST("?action=login_user")
    @FormUrlEncoded
    fun getUsersPost(@Field("username") username: String?,
                         @Field("password") password: String): Call<UsersResponse>

    @POST("?action=insert_user")
    @FormUrlEncoded
    fun insertUsersPost(@Field("username") username: String?,
                         @Field("password") password: String): Call<Users>

    @GET("?action=delete_review&id_review=id_review")
    fun deleteReview(@Query("id_review") id: String?): Call<Movie>
}


//**App Utils**

object ApiUtils {

    val BASE_URL = "http://10.4.139.105/moviereview/"

    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)
}