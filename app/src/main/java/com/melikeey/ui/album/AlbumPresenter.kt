package com.melikeey.ui.album

import com.melikeey.base.BaseNavigator
import com.melikeey.base.BasePresenter
import com.melikeey.model.Data
import com.melikeey.model.GetAlbumResponse
import com.melikeey.networking.MusicApiService
import com.melikeey.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AlbumPresenter<V : AlbumMvpView?>
@Inject internal constructor(apiService: MusicApiService?, schedulerProvider: SchedulerProvider?, compositeDisposable: CompositeDisposable?, navigator: BaseNavigator?) : BasePresenter<V>(apiService, schedulerProvider, compositeDisposable, navigator), AlbumMvpPresenter<V> {

    lateinit var albumList: MutableList<Data>

    override fun getAlbumFromService(artistId: String?) {
        compositeDisposable.add(apiService.getAlbums(artistId!!).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui()).subscribe({ album: GetAlbumResponse? ->
            if (album != null) {
                albumList = album.data as MutableList<Data>
                mvpView!!.setAlbumList(albumList)
            } else {
                mvpView!!.showMessage("Error")
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