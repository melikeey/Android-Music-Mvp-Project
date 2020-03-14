package com.melikeey.base;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.melikeey.networking.MusicApiService;
import com.melikeey.util.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final MusicApiService mAPIService;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;
    private final BaseNavigator navigator;

    private V mMvpView;

    @Inject
    public BasePresenter(MusicApiService apiService, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseNavigator navigator) {

        this.mAPIService = apiService;
        this.mCompositeDisposable = compositeDisposable;
        this.mSchedulerProvider = schedulerProvider;
        this.navigator = navigator;
    }

    @Override
    public void onViewPrepared() {
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
    }

    protected V getMvpView() {
        return mMvpView;
    }

    protected MusicApiService getApiService() {
        return mAPIService;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    private BaseNavigator getNavigator() {
        return navigator;
    }

    @Override
    public void openActivity(Class<?> activity, boolean shouldFinishPrevious) {
        getNavigator().openActivity(activity, shouldFinishPrevious);
    }

    @Override
    public void openActivity(Intent intent) {
        getNavigator().openActivity(intent);
    }

    @Override
    public void openFragment(BaseFragment fragment, BaseNavigator.Transaction transaction, Boolean addToBackStack) {
        getNavigator().openFragment(fragment, transaction, addToBackStack);
    }

    @Override
    public void openChildFragment(BaseFragment baseFragment, BaseFragment selectedFragment, @IdRes int containerId) {
        getNavigator().openChildFragment(baseFragment, selectedFragment, containerId);
    }

    @Override
    public List<Fragment> getFragments() {
        return getNavigator().getFragments();
    }

    @Override
    public void popBackStack() {
        getNavigator().popBackStack();
    }

    @Override
    public void popBackStackImmediate() {
        getNavigator().popBackStackImmediate();
    }
}