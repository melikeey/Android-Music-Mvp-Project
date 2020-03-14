package com.melikeey.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.melikeey.R;
import com.melikeey.databinding.FragmentAlbumBinding;

public class TestFragmentFirst extends BaseFragment {

    static final String TAG = "TestFragmentFirst";

    static TestFragmentFirst newInstance() {

        Bundle args = new Bundle();
        TestFragmentFirst fragment = new TestFragmentFirst();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       FragmentAlbumBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void setUp(View view) {}
}