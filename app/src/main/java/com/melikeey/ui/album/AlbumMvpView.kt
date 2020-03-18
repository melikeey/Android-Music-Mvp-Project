package com.melikeey.ui.album

import com.melikeey.base.MvpView
import com.melikeey.model.Data

interface AlbumMvpView : MvpView {
    fun setAlbumList(albumList: List<Data?>?)
    fun showEmptyWarning();
}