package com.melikeey.ui.main

import com.melikeey.base.BaseNavigator
import com.melikeey.base.BasePresenter
import com.melikeey.networking.MusicApiService
import com.melikeey.ui.search.SearchFragment
import com.melikeey.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V : MainMvpView?> @Inject internal constructor(apiService: MusicApiService?, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable?, navigator: BaseNavigator?) : BasePresenter<V>(apiService, schedulerProvider, compositeDisposable, navigator), MainMvpPresenter<V> {

    override fun onViewPrepared() {
        showInitialFragment()
    }

    override fun showInitialFragment() {
        openFragment(SearchFragment.newInstance(), BaseNavigator.Transaction.ADD, false)
    }
}