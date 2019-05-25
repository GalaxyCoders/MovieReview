package com.mqa.android.moviereview.module.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.api.APIService
import com.mqa.android.moviereview.api.ApiUtils
import com.mqa.android.moviereview.model.Users
import com.mqa.android.moviereview.model.UsersResponse
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn.onClick {
            val dialog = progressDialog(message = "Please wait a bit…", title = "Signing up")
            dialog.isIndeterminate = true
            dialog.show()

            when {
                usernameET.text.isEmpty() -> {
                    usernameET.error = "Username harus diisi"
                    dialog.dismiss()
                }
                passwordET.text.isEmpty() -> {
                    passwordET.error = "Password harus diisi"
                    dialog.dismiss()
                }
                confirmpasswordET.text.isEmpty() -> {
                    confirmpasswordET.error = "Confirm Password harus diisi"
                    dialog.dismiss()
                }
                passwordET.text.toString() != confirmpasswordET.text.toString()->{
                    confirmpasswordET.error = "Password tidak sesuai"
                    dialog.dismiss()
                }
                passwordET.text.toString() == confirmpasswordET.text.toString() ->{
                    //Variable declaration
                    val mAPIService: APIService?
                    mAPIService = ApiUtils.apiService

                    //Some Button click
                    mAPIService.insertUsersPost(usernameET.text.toString(), confirmpasswordET.text.toString()).enqueue(object : Callback<Users> {

                        override fun onResponse(call: Call<Users>, response: Response<Users>) {

                            Log.i("", "post submitted to API." + response.body()!!)

                            if (response.isSuccessful) {

                                Log.i("", "post status to API" + response.code())
                                Log.i("", "post error to API" + response.errorBody())
                                Log.i("", "post msg to API" + response.message())
                                dialog.dismiss()
                                toast("Berhasil mendaftar")
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)

                            }
                        }

                        override fun onFailure(call: Call<Users>, t: Throwable) {
                            val parentLayout = findViewById<View>(android.R.id.content)
                            parentLayout.snackbar("Masukkan data dengan benar")
                            dialog.dismiss()
                        }
                    })
                }
            }


        }
    }
}
