package com.melikeey.di.module


import com.melikeey.ui.album.AlbumAdapter
import com.melikeey.ui.album.AlbumMvpPresenter
import com.melikeey.ui.album.AlbumMvpView
import com.melikeey.ui.album.AlbumPresenter
import com.melikeey.ui.player.PlayAdapter
import com.melikeey.ui.player.PlayMvpPresenter
import com.melikeey.ui.player.PlayMvpView
import com.melikeey.ui.player.PlayPresenter
import com.melikeey.ui.search.SearchAdapter
import com.melikeey.ui.search.SearchMvpPresenter
import com.melikeey.ui.search.SearchMvpView
import com.melikeey.ui.search.SearchPresenter
import com.melikeey.ui.track.TrackAdapter
import com.melikeey.ui.track.TrackMvpPresenter
import com.melikeey.ui.track.TrackMvpView
import com.melikeey.ui.track.TrackPresenter
import dagger.Module
import dagger.Provides

@Module
class ViewModule {

    @Provides
    fun provideAlbumMvpPresenter(presenter: AlbumPresenter<AlbumMvpView>): AlbumMvpPresenter<AlbumMvpView> {
        return presenter
    }

    @Provides
    fun provideSearchMvpPresenter(presenter: SearchPresenter<SearchMvpView>): SearchMvpPresenter<SearchMvpView> {
        return presenter
    }

    @Provides
    fun provideTrackMvpPresenter(presenter: TrackPresenter<TrackMvpView>): TrackMvpPresenter<TrackMvpView> {
        return presenter
    }

    @Provides
    fun providePlayMvpPresenter(presenter: PlayPresenter<PlayMvpView>): PlayMvpPresenter<PlayMvpView> {
        return presenter
    }

    @Provides
    fun provideTrackAdapter(): TrackAdapter {
        return TrackAdapter()
    }


    @Provides
    fun provideAlbumAdapter(): AlbumAdapter {
        return AlbumAdapter()
    }

    @Provides
    fun provideSearchAdapter(): SearchAdapter {
        return SearchAdapter()
    }


    @Provides
    fun providePlayAdapter(): PlayAdapter {
        return PlayAdapter()
    }
}