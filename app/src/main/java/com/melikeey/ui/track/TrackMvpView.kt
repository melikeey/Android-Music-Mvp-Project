package com.melikeey.ui.track

import com.melikeey.base.MvpView
import com.melikeey.model.Data

interface TrackMvpView : MvpView {
    fun setTrackList(trackList: List<Data?>?)
}