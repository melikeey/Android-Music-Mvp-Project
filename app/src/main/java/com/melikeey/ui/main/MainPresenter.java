package com.melikeey.ui.main;

import com.melikeey.base.BaseNavigator;
import com.melikeey.base.BasePresenter;
import com.melikeey.networking.MusicApiService;
import com.melikeey.ui.search.SearchFragment;
import com.melikeey.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    MainPresenter(MusicApiService apiService, @NonNull SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseNavigator navigator) {
        super(apiService, schedulerProvider, compositeDisposable, navigator);
    }

    @Override
    public void onViewPrepared() {

        showInitialFragment();
    }

    @Override
    public void showInitialFragment() {
            openFragment(SearchFragment.newInstance(), BaseNavigator.Transaction.ADD, false);
    }
}