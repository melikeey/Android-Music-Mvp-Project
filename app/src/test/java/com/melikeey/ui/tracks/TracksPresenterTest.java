package com.melikeey.ui.tracks;

import com.melikeey.model.Album;
import com.melikeey.model.Artist;
import com.melikeey.model.Data;
import com.melikeey.networking.MusicApiService;
import com.melikeey.ui.track.TrackMvpView;
import com.melikeey.ui.track.TrackPresenter;
import com.melikeey.util.rx.SchedulerProvider;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class TracksPresenterTest {

    private TrackPresenter<TrackMvpView> presenter;

    @Mock
    MusicApiService apiService;

    @Mock
    SchedulerProvider schedulerProvider;

    @Mock
    TrackMvpView view;

    private List<Data> trackList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        presenter = new TrackPresenter<>(apiService, schedulerProvider, new CompositeDisposable(), null);
        presenter.onAttach(view);
    }

    @Test
    public void testSetTrackList() {

        setMockDataList();

        presenter.trackList = trackList;

        view.setTrackList(trackList);

        TestCase.assertEquals(trackList.size(), 1);

    }

    @Test
    public void testEmptyTrackList() {

        presenter.trackList = trackList;

        view.showMessage("Error");

        TestCase.assertEquals(trackList.size(), 0);
    }

    private void setMockDataList() {
        String TEST_ID = "2114406";
        String TEST_TITLE = "Nothing Else Matter";
        String TEST_COVER_BIG = "https://e-cdns-images.dzcdn.net/images/cover/f1c31620f0e108b707ce1a1af0954158/500x500-000000-80-0-0.jpg\"";

        Data data = new Data(TEST_ID, new Artist("","","",""), new Album("","","",""), TEST_TITLE, TEST_COVER_BIG);
        trackList.add(data);
    }



    @After
    public void tearDown() throws Exception {

        apiService = null;
        view = null;
        schedulerProvider = null;
        presenter = null;
        trackList = null;
    }
}