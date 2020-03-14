package com.melikeey.ui.main;

import com.melikeey.networking.MusicApiService;
import com.melikeey.util.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

public class MainPresenterTest {

    private MainPresenter<MainMvpView> presenter;

    @Mock
    MainMvpView view;

    @Mock
    MusicApiService apiService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        TestScheduler mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        presenter = new MainPresenter<>(apiService, testSchedulerProvider, new CompositeDisposable(), null);
        presenter.onAttach(view);
    }

    @After
    public void tearDown() throws Exception {

    }
}
