package com.melikeey.ui.search;


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
@Config(sdk = 28)

@RunWith(RobolectricTestRunner.class)
public class SearchAdapterTest extends BaseUnitTest {

    private SearchAdapter searchAdapter;

    private List<Data> searchList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        searchAdapter = new SearchAdapter();

        setMockDataList();

        searchAdapter.addSearchResponseList(searchList);
    }

    @After
    public void tearDown() throws Exception {

        searchAdapter = null;
        searchList = null;
    }

    @Test
    public void itemCountTest() {
        TestCase.assertEquals(searchAdapter.getItemCount(), searchList.size());
    }

    @Test
    public void notNullTest() {
        TestCase.assertNotNull(searchAdapter);
    }
    private void setMockDataList() {
        String TEST_ID = "2114406";
        String TEST_TITLE = "Nothing Else Matter";
        String TEST_COVER_BIG = "https://e-cdns-images.dzcdn.net/images/cover/f1c31620f0e108b707ce1a1af0954158/500x500-000000-80-0-0.jpg\"";

        searchList.add(new Data(TEST_ID, new Artist("","",""), new Album("","","",""), TEST_TITLE, TEST_COVER_BIG,""));
    }

}