package com.melikeey.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.melikeey.R;
import com.melikeey.di.component.ActivityComponent;
import com.melikeey.ui.main.MainActivity;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment implements MvpView, ToolbarListener, OnBackPressedListener, MenuItem.OnMenuItemClickListener {

    private BaseActivity baseActivity;

    private MainActivity mainActivity;

    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUp(view);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
            mainActivity = (MainActivity) baseActivity;

            toolbar = mainActivity.getToolbar();
        }
    }

    @Override
    public void restartApp() {
        if (baseActivity != null) {
            baseActivity.restartApp();
        }
    }

    @Override
    public void showMessage(String message) {
        if (baseActivity != null) {
            baseActivity.showMessage(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (baseActivity != null) {
            return baseActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void showToolbar() {
        if (mainActivity != null && toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideToolbar() {
        if (mainActivity != null && toolbar != null) {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected ActivityComponent getActivityComponent() {
        if (baseActivity != null) {
            return baseActivity.getActivityComponent();
        }

        return null;
    }

    protected BaseActivity getBaseActivity() {
        return baseActivity;
    }

    protected abstract void setUp(View view);

    private View.OnClickListener navigationIconListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            BaseFragment.this.getBaseActivity().onBackPressed();
        }
    };

    @Override
    public void onBackPressed() {
    }

    @Override
    public void prepareToolbar(NavigationIcon navIcon, String title) {

        showToolbar();

        if (toolbar == null) {
            toolbar = getBaseActivity().findViewById(R.id.toolbar);
        }

        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(navigationIconListener);

        handleNavIcon(navIcon);
    }

    private void handleNavIcon(NavigationIcon navIcon) {

        switch (navIcon) {
            case BACK:
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
                toolbar.setNavigationOnClickListener(navigationIconListener);

                break;

            case NO_NAV_ICON:
                toolbar.setNavigationIcon(null);
                toolbar.setNavigationOnClickListener(null);

                break;

            default:
                toolbar.setNavigationIcon(null);
                toolbar.setNavigationOnClickListener(null);

                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public enum NavigationIcon {
        NO_NAV_ICON,
        BACK
    }
}