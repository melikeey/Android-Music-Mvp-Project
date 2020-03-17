package com.melikeey.ui.album;


import com.melikeey.model.Album;
import com.melikeey.model.Artist;
import com.melikeey.model.Data;
import com.melikeey.ui.base.BaseUnitTest;

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

public class AlbumAdapterTest extends BaseUnitTest {

    private AlbumAdapter albumAdapter;

    private List<Data> albumList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        albumAdapter = new AlbumAdapter();
        setMockDataList();

        String TEST_ARTIST = "Metallica";
        albumAdapter.addAlbum(albumList, TEST_ARTIST);
    }

    @After
    public void tearDown() throws Exception {

        albumAdapter = null;
        albumList = null;
    }

    @Test
    public void itemCountTest() {
        TestCase.assertEquals(albumAdapter.getItemCount(), albumList.size());
    }

    @Test
    public void notNullTest() {
        TestCase.assertNotNull(albumAdapter);
    }

    private void setMockDataList() {

        String TEST_ID = "2114406";
        String TEST_TITLE = "Nothing Else Matter";
        String TEST_COVER_BIG = "https://e-cdns-images.dzcdn.net/images/cover/f1c31620f0e108b707ce1a1af0954158/500x500-000000-80-0-0.jpg\"";

        Data data = new Data(TEST_ID, new Artist("","",""), new Album("","","",""), TEST_TITLE, TEST_COVER_BIG, "");
        albumList.add(data);
    }
}