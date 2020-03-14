package com.melikeey.ui.main;

import com.melikeey.base.MvpPresenter;
import com.melikeey.di.PerActivity;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void showInitialFragment();

}