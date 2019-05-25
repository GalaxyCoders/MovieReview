package com.mqa.android.moviereview.module.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mqa.android.moviereview.module.fragment.home.HomeFragment
import com.mqa.android.moviereview.R
import com.mqa.android.moviereview.module.fragment.MyReview.MyReviewFragment
import com.mqa.android.moviereview.utils.PrefsApplication
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    val id: String= Prefs.getString("userId", "1")

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.homeInstance()
                addFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (Prefs.contains("userId"))
                    startActivity<AddActivity>()
                else
                    startActivity<LoginActivity>()
            }
            R.id.navigation_notifications -> {
                if (Prefs.contains("userId")) {
                    val myFragment = MyReviewFragment.myInstance()
                    addFragment(myFragment)
                } else
                    startActivity<LoginActivity>()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val key: Int = intent.getIntExtra("key", 0)
        Log.v("kunci", "" + key)
        Log.v("user", "" + id)
        if (key == 1) {
            val myFragment = MyReviewFragment.myInstance()
            addFragment(myFragment)
            navigation.menu.getItem(2).isChecked = true
        } else if (key == 0) {
            addFragment(HomeFragment.homeInstance())
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
