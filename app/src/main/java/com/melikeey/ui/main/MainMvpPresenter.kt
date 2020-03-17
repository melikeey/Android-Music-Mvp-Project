package com.melikeey.ui.main

import com.melikeey.base.MvpPresenter
import com.melikeey.di.PerActivity

@PerActivity
interface MainMvpPresenter<V : MainMvpView?> : MvpPresenter<V> {
    fun showInitialFragment()
}