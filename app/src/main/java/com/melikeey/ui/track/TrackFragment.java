package com.melikeey.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melikeey.R;
import com.melikeey.base.BaseFragment;
import com.melikeey.databinding.FragmentTrackBinding;
import com.melikeey.di.component.ActivityComponent;
import com.melikeey.model.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class TrackFragment extends BaseFragment implements TrackMvpView {

    @Inject
    TrackMvpPresenter<TrackMvpView> mPresenter;

    @Inject
    TrackAdapter trackAdapter;

    private FragmentTrackBinding mBinding;

    private static String albumId, mAlbumCover, mAlbumTitle, mArtist;

    public static TrackFragment newInstance(String mAlbumId, String albumCover, String albumTitle, String artist) {

        Bundle args = new Bundle();
        TrackFragment fragment = new TrackFragment();
        fragment.setArguments(args);

        albumId = mAlbumId;
        mAlbumTitle = albumTitle;
        mAlbumCover = albumCover;
        mArtist = artist;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_track, container, false);

        ActivityComponent component = getActivityComponent();

        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }

        return mBinding.getRoot();
    }

    private void setUpRecyclerView() {
        mBinding.rv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());

        mBinding.rv.setLayoutManager(layoutManager);

        mBinding.rv.setAdapter(trackAdapter);
    }

    @Override
    protected void setUp(View view) {
        mPresenter.getTracksResponse(albumId);
    }

    @Override
    public void onResume() {
        super.onResume();

        prepareToolbar(NavigationIcon.BACK, getResources().getString(R.string.tracks));
    }

    @Override
    public void setTrackList(List<Data> trackList) {

        Picasso.get().load(mAlbumCover).into(mBinding.iv);

        mBinding.tvAlbumName.setText(mAlbumTitle);
        mBinding.tvArtistName.setText(mArtist);

        trackAdapter.addTrackList(trackList);
        trackAdapter.notifyDataSetChanged();

        setUpRecyclerView();
    }
}
