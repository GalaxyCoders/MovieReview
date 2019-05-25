package com.mqa.android.moviereview.module.activity

import android.content.ContextWrapper
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.api.APIService
import com.mqa.android.moviereview.api.ApiUtils
import com.mqa.android.moviereview.model.Movie2
import com.mqa.android.moviereview.model.Users
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.mqa.android.moviereview.model.UsersResponse
import com.pixplicity.easyprefs.library.Prefs
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity


class LoginActivity : AppCompatActivity() {

    private var id: String = String()
    private var user: MutableList<Users> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.onClick {
            val dialog = progressDialog(message = "Please wait a bit…", title = "Signing in")
            dialog.isIndeterminate = true
            dialog.show()

            if (nameET.text.isEmpty())
                nameET.error = "Username harus diisi"
            else if (passET.text.isEmpty())
                passET.error = "Password harus diisi"

            //Variable declaration
            val mAPIService: APIService?
            mAPIService = ApiUtils.apiService

            //Some Button click
            mAPIService.getUsersPost(nameET.text.toString(), passET.text.toString()).enqueue(object : Callback<UsersResponse> {

                override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {

                    Log.i("", "post submitted to API." + response.body()!!)

                    if (response.isSuccessful) {

//                        Log.e("TAG", "response 33: " + response.body()!!.data.forEach { it.userId })
//                        id = response.body()!!.data.toString()
                        user.addAll(response.body()!!.data)
                        user.map {
                            id = it.userId.toString()
                        }

                        Log.i("", "post registration to API " + id)
                        Log.i("", "post status to API" + response.code())
                        Log.i("", "post error to API" + response.errorBody())
                        Log.i("", "post msg to API" + response.message())
                        dialog.dismiss()

                        Prefs.putString("userId", id)

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("key", 0)
                        startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    val parentLayout = findViewById<View>(android.R.id.content)
                    parentLayout.snackbar("Data yang Anda masukkan salah")
                    dialog.dismiss()
                }
            })
        }

        createBtn.onClick {
            startActivity<RegisterActivity>()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
