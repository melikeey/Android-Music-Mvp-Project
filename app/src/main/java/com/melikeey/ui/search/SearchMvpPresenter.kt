package com.melikeey.ui.search

import com.melikeey.base.MvpPresenter

interface SearchMvpPresenter<V : SearchMvpView?> : MvpPresenter<V> {
    fun getSearchResult(searchQuery: String?)
}