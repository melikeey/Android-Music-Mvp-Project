package com.melikeey.ui.tracks;


import com.melikeey.model.Album;
import com.melikeey.model.Artist;
import com.melikeey.model.Data;
import com.melikeey.ui.base.BaseUnitTest;
import com.melikeey.ui.track.TrackAdapter;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)

public class TracksAdapterTest extends BaseUnitTest {

    private TrackAdapter trackAdapter;

    private List<Data> trackList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        trackAdapter = new TrackAdapter();

        setMockDataList();
        trackAdapter.addTrackList(trackList);
    }

    @After
    public void tearDown() throws Exception {

        trackAdapter = null;
        trackList = null;
    }

    @Test
    @Override
    public void setUpTest() {
    }

    @Test
    public void itemCountTest() {
        TestCase.assertEquals(trackAdapter.getItemCount(), trackList.size());
    }

    @Test
    public void notNullTest() {
        TestCase.assertNotNull(trackAdapter);
    }

    private void setMockDataList() {
        String TEST_ID = "2114406";
        String TEST_TITLE = "Nothing Else Matter";
        String TEST_COVER_BIG = "https://e-cdns-images.dzcdn.net/images/cover/f1c31620f0e108b707ce1a1af0954158/500x500-000000-80-0-0.jpg\"";

        Data data = new Data(TEST_ID, new Artist("","","",""), new Album("","","",""), TEST_TITLE, TEST_COVER_BIG);
        trackList.add(data);
    }
}