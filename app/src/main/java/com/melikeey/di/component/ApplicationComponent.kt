package com.melikeey.di.component

import android.content.Context
import com.melikeey.MusicApp
import com.melikeey.di.ApplicationContext
import com.melikeey.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@ApplicationContext
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun MusicApp.inject()

    @ApplicationContext
    fun context(): Context
}