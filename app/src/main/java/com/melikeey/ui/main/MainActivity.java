package com.melikeey.ui.main;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.melikeey.R;
import com.melikeey.base.BaseActivity;
import com.melikeey.base.BaseNavigator;
import com.melikeey.databinding.ActivityMainBinding;
import com.melikeey.ui.search.SearchFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    ActivityMainBinding mainBinding;

    @Inject
    public MainMvpPresenter<MainMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setUp() {

        mPresenter.onViewPrepared();

        setSupportActionBar(mainBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mPresenter.openFragment(SearchFragment.newInstance(),
                BaseNavigator.Transaction.REPLACE,
                false);
    }

    @Override
    protected void onDestroy() {

        mPresenter.onDetach();
        super.onDestroy();
    }

    public Toolbar getToolbar() {
        if (mainBinding == null) {
            return findViewById(R.id.toolbar);
        } else {
            return mainBinding.toolbar;
        }
    }
}