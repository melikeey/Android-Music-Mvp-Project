package com.melikeey.base;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.melikeey.R;

import java.util.List;

public class BaseNavigator implements Navigation {

    private AppCompatActivity mActivity;

    public BaseNavigator(final AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void openActivity(Class<?> activity, boolean shouldFinishPrevious) {

        try {
            mActivity.startActivity(new Intent(mActivity, activity));

            if (shouldFinishPrevious) {
                mActivity.finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openActivity(Intent intent) {

        try {
            mActivity.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void openFragment(BaseFragment fragment, Transaction transaction, Boolean addToBackStack) {

        try {
            FragmentTransaction fragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();

            switch (transaction) {

                case ADD:
                    fragmentTransaction.add(R.id.fl_main, fragment);

                    break;

                case REPLACE:
                    fragmentTransaction.replace(R.id.fl_main, fragment);

                    break;

                default:
                    fragmentTransaction.replace(R.id.fl_main, fragment);

                    break;
            }

            if (addToBackStack == null) {
                fragmentTransaction.addToBackStack(null);
            } else if (addToBackStack)
                fragmentTransaction.addToBackStack(fragment.getClass().getName());

            fragmentTransaction.commit();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void openChildFragment(BaseFragment baseFragment, BaseFragment selectedFragment, @IdRes int containerId) {

        try {
            FragmentTransaction transaction = baseFragment.getChildFragmentManager().beginTransaction();
            transaction.replace(containerId, selectedFragment);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fragment> getFragments() {
        return mActivity.getSupportFragmentManager().getFragments();
    }

    @Override
    public void popBackStack() {

        try {
            mActivity.getSupportFragmentManager().popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void popBackStackImmediate() {

        try {
            mActivity.getSupportFragmentManager().popBackStackImmediate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum Transaction {
        REPLACE,
        ADD
    }
}