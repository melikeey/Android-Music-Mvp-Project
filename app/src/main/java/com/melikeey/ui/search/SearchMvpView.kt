package com.melikeey.ui.search

import com.melikeey.base.MvpView
import com.melikeey.model.Data

interface SearchMvpView : MvpView {
    fun setSearchResponse(data: List<Data?>?)
}