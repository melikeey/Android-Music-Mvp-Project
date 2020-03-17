package com.melikeey.ui.player

import com.melikeey.base.BaseNavigator
import com.melikeey.base.BasePresenter
import com.melikeey.networking.MusicApiService

import com.melikeey.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class PlayPresenter<V : PlayMvpView?> @Inject constructor(apiService: MusicApiService?, schedulerProvider: SchedulerProvider?, compositeDisposable: CompositeDisposable?, navigator: BaseNavigator?) : BasePresenter<V>(apiService, schedulerProvider, compositeDisposable, navigator), PlayMvpPresenter<V>
