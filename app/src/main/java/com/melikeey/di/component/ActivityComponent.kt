package com.melikeey.di.component

import com.melikeey.di.ActivityContext
import com.melikeey.di.PerActivity
import com.melikeey.di.module.ActivityModule
import com.melikeey.di.module.NetworkModule
import com.melikeey.di.module.ViewModule
import com.melikeey.ui.album.AlbumFragment
import com.melikeey.ui.main.MainActivity
import com.melikeey.ui.search.SearchFragment
import com.melikeey.ui.track.TrackFragment
import dagger.Component

@PerActivity
@ActivityContext
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, NetworkModule::class, ViewModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(albumFragment: AlbumFragment)

    fun inject(searchFragment: SearchFragment)

    fun inject(trackFragment: TrackFragment)

}