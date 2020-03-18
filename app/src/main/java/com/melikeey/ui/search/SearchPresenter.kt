package com.melikeey.ui.search

import android.util.Log
import com.melikeey.base.BaseNavigator
import com.melikeey.base.BasePresenter
import com.melikeey.model.Data
import com.melikeey.model.GetSearchResponse
import com.melikeey.networking.MusicApiService
import com.melikeey.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchPresenter<V : SearchMvpView?>

@Inject constructor(apiService: MusicApiService?, schedulerProvider: SchedulerProvider?, compositeDisposable: CompositeDisposable?, navigator: BaseNavigator?) : BasePresenter<V>(apiService, schedulerProvider, compositeDisposable, navigator), SearchMvpPresenter<V> {

    lateinit var searchList: MutableList<Data>

    override fun getSearchResult(searchQuery: String?) {
        compositeDisposable.add(apiService.getSearch(searchQuery).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui()).subscribe({ data: GetSearchResponse? ->

            if (data != null) {
                searchList = data.data as MutableList<Data>
                mvpView!!.setSearchResponse(searchList)
            } else {
                mvpView!!.showMessage("No Data")
            }
        }) { throwable: Throwable? ->
            if (throwable != null) {
                if (!mvpView!!.isNetworkConnected()) {
                    mvpView!!.showMessage("Network Problem")
                } else {
                    Log.e("hata",throwable.message)
                    mvpView!!.showMessage("Error")
                }
            }
        })
    }
}