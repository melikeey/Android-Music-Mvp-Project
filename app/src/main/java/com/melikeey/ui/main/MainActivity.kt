package com.melikeey.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.melikeey.R
import com.melikeey.base.BaseActivity
import com.melikeey.base.BaseNavigator
import com.melikeey.databinding.ActivityMainBinding
import com.melikeey.ui.search.SearchFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {
    var mainBinding: ActivityMainBinding? = null
    @JvmField
    @Inject
    var mPresenter: MainMvpPresenter<MainMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityComponent!!.inject(this)
        mPresenter!!.onAttach(this)
        setUp()
    }

    override fun onResume() {
        super.onResume()
    }

    public override fun setUp() {
        mPresenter!!.onViewPrepared()
        setSupportActionBar(mainBinding!!.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
        mPresenter!!.openFragment(SearchFragment.newInstance(),
                BaseNavigator.Transaction.REPLACE,
                false)
    }

    override fun onDestroy() {
        mPresenter!!.onDetach()
        super.onDestroy()
    }

    override val toolbar: Toolbar
        get() = if (mainBinding == null) {
            findViewById(R.id.toolbar)
        } else {
            mainBinding!!.toolbar
        }
}