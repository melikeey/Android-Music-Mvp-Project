package com.melikeey.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.melikeey.base.TestActivity;

import org.robolectric.Robolectric;

public abstract class BaseUnitTest {

    AppCompatActivity activity;

    protected AppCompatActivity startFragment(Fragment fragment) {

        activity = Robolectric.buildActivity(TestActivity.class)
                .create()
                .start()
                .resume()
                .get();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, fragment.getClass().getName());
        fragmentTransaction.commit();

        return activity;
    }

    protected String getLastFragmentName(Fragment fragment) {

        if (fragment.getFragmentManager() != null && fragment.getFragmentManager().getFragments().size() > 0) {
            return fragment.getFragmentManager().getFragments().get(fragment.getFragmentManager().getFragments().size() - 1).getClass().getSimpleName();
        }

        return "";
    }

    protected AppCompatActivity getActivity() {
        return activity;
    }

    protected abstract void setUpTest();
}

