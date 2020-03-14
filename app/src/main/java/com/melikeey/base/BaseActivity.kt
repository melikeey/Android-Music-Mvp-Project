package com.melikeey.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melikeey.MusicApp
import com.melikeey.di.component.ActivityComponent
import com.melikeey.di.component.DaggerActivityComponent
import com.melikeey.di.module.ActivityModule
import com.melikeey.di.module.NetworkModule
import com.melikeey.di.module.ViewModule
import com.melikeey.ui.main.MainActivity
import com.melikeey.util.NetworkUtils

abstract class BaseActivity : AppCompatActivity(), MvpView {

    var activityComponent: ActivityComponent? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .viewModule(ViewModule())
                .networkModule(NetworkModule())
                .applicationComponent((application as MusicApp).applicationComponent)
                .build()
    }


    override fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finishAffinity()
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    protected abstract fun setUp()

    override fun onBackPressed() {
        implementOnBackPressedForFragments()

        super.onBackPressed()
    }

    private fun implementOnBackPressedForFragments() {
        val fragments = supportFragmentManager.fragments

        val reversedFragmentList = fragments.asReversed()

        for (f in reversedFragmentList) {

            if (f is BaseFragment) {
                f.onBackPressed()

                break
            }
        }
    }
}