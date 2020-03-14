package com.melikeey.ui.album

import com.melikeey.base.MvpPresenter

interface AlbumMvpPresenter<V : AlbumMvpView?> : MvpPresenter<V> {
    fun getAlbumFromService(artistId: String?)
}