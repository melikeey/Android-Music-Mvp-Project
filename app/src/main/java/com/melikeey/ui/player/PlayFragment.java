package com.melikeey.ui.player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.melikeey.R;
import com.melikeey.base.BaseFragment;
import com.melikeey.databinding.FragmentPlayBinding;
import com.melikeey.databinding.FragmentTrackBinding;
import com.melikeey.di.component.ActivityComponent;
import com.melikeey.ui.track.TrackAdapter;
import com.melikeey.ui.track.TrackFragment;
import com.melikeey.ui.track.TrackMvpPresenter;
import com.melikeey.ui.track.TrackMvpView;

import java.io.IOException;

import javax.inject.Inject;

public class PlayFragment extends BaseFragment implements PlayMvpView  {

    @Inject
    PlayMvpPresenter<PlayMvpView> mPresenter;

    @Inject
    PlayAdapter playAdapter;

   private FragmentPlayBinding mBinding;

   private static String mPreview;

    public static PlayFragment newInstance(String preview) {

        Bundle args = new Bundle();
        PlayFragment fragment = new PlayFragment();
        fragment.setArguments(args);
        mPreview  = preview;

        return fragment;
    }

    @Override
    protected void setUp(View view) {

        MediaPlayer mp = new MediaPlayer();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false);


        ActivityComponent component = getActivityComponent();

        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
        return mBinding.getRoot();
    }
}
