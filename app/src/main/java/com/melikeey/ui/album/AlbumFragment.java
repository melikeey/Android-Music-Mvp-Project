package com.melikeey.ui.album;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.melikeey.R;
import com.melikeey.base.BaseFragment;
import com.melikeey.databinding.FragmentAlbumBinding;
import com.melikeey.di.component.ActivityComponent;
import com.melikeey.model.Artist;
import com.melikeey.model.Data;

import java.util.List;

import javax.inject.Inject;

public class AlbumFragment extends BaseFragment implements AlbumMvpView {

    @Inject
    AlbumMvpPresenter<AlbumMvpView> mPresenter;

    @Inject
    AlbumAdapter albumAdapter;

    private FragmentAlbumBinding mBinding;

    private static Artist mArtist;

    public static AlbumFragment newInstance(Artist artist) {

        Bundle args = new Bundle();
        AlbumFragment fragment = new AlbumFragment();
        fragment.setArguments(args);

        mArtist = artist;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false);

        ActivityComponent component = getActivityComponent();

        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);

        }

        return mBinding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        mPresenter.getAlbumFromService(mArtist.getId());
    }

    @Override
    public void setAlbumList(List<Data> data) {


        albumAdapter.addAlbum(data, mArtist.getName());

        albumAdapter.setFragment(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getBaseActivity(), 2);

        mBinding.rvMyTrips.setLayoutManager(layoutManager);
        mBinding.rvMyTrips.setHasFixedSize(true);
        mBinding.rvMyTrips.setAdapter(albumAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        prepareToolbar(NavigationIcon.BACK, getResources().getString(R.string.album));
    }

    @Override
    public void showEmptyWarning() {
            mBinding.tvTips.setText(getResources().getString(R.string.there_is_no_album));
    }
}
