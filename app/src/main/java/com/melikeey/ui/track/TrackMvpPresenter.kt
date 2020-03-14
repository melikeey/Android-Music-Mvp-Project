package com.melikeey.ui.track

import com.melikeey.base.MvpPresenter

interface TrackMvpPresenter<V : TrackMvpView?> : MvpPresenter<V> {
    fun getTracksResponse(albumId: String?)
}