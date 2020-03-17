package com.melikeey.ui.search;

import com.melikeey.model.Album;
import com.melikeey.model.Artist;
import com.melikeey.model.Data;
import com.melikeey.networking.MusicApiService;
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

public class SearchPresenterTest {

    private SearchPresenter<SearchMvpView> presenter;

    @Mock
    MusicApiService apiService;

    @Mock
    SchedulerProvider schedulerProvider;

    @Mock
    SearchMvpView view;

    private List<Data> searchList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        presenter = new SearchPresenter<>(apiService, schedulerProvider, new CompositeDisposable(), null);
        presenter.onAttach(view);
    }

    @Test
    public void testSetSearchResponse() {

        setMockDataList();

        presenter.searchList = searchList;

        view.setSearchResponse(searchList);

        TestCase.assertEquals(searchList.size(), 1);

    }

    @Test
    public void testSearchListAsEmpty() {

        presenter.searchList = searchList;

        view.showMessage("Error");

        TestCase.assertEquals(searchList.size(), 0);

    }

    @After
    public void tearDown() throws Exception {

        apiService = null;
        view = null;
        schedulerProvider = null;
        presenter = null;
        searchList = null;
    }


    private void setMockDataList() {
        String TEST_ID = "2114406";
        String TEST_TITLE = "Nothing Else Matter";
        String TEST_COVER_BIG = "https://e-cdns-images.dzcdn.net/images/cover/f1c31620f0e108b707ce1a1af0954158/500x500-000000-80-0-0.jpg\"";

        Data data = new Data(TEST_ID, new Artist("","",""), new Album("","","",""), TEST_TITLE, TEST_COVER_BIG,"");
        searchList.add(data);
    }
}