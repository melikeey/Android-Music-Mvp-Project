package com.melikeey.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.melikeey.base.BaseNavigator
import com.melikeey.di.ActivityContext
import com.melikeey.di.PerActivity
import com.melikeey.ui.main.MainMvpPresenter
import com.melikeey.ui.main.MainMvpView
import com.melikeey.ui.main.MainPresenter
import com.melikeey.util.rx.AppSchedulerProvider
import com.melikeey.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    fun provideBaseNavigator(): BaseNavigator {
        return BaseNavigator(activity)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @PerActivity
    fun provideMainPresenter(presenter: MainPresenter<MainMvpView>): MainMvpPresenter<MainMvpView> {
        return presenter
    }
}