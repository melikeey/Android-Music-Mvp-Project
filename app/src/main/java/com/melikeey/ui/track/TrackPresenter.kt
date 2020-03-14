package com.melikeey.ui.track

import com.melikeey.base.BaseNavigator
import com.melikeey.base.BasePresenter
import com.melikeey.model.Data
import com.melikeey.model.GetSearchResponse
import com.melikeey.networking.MusicApiService
import com.melikeey.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class TrackPresenter<V : TrackMvpView?> @Inject constructor(apiService: MusicApiService?, schedulerProvider: SchedulerProvider?, compositeDisposable: CompositeDisposable?, navigator: BaseNavigator?) : BasePresenter<V>(apiService, schedulerProvider, compositeDisposable, navigator), TrackMvpPresenter<V> {

    lateinit var trackList: MutableList<Data>

    override fun getTracksResponse(albumId: String?) {
        compositeDisposable.add(apiService.getTracks(albumId!!).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui()).subscribe({ data: GetSearchResponse? ->
            if (data != null) {
                trackList = data.data as MutableList<Data>
                mvpView!!.setTrackList(trackList)
            } else {
                mvpView!!.showMessage("No Data")
            }
        }) { throwable: Throwable? ->
            if (throwable != null) {
                if (!mvpView!!.isNetworkConnected()) {
                    mvpView!!.showMessage("Network Problem")
                } else {
                    mvpView!!.showMessage("Error")
                }
            }
        })
    }
}