package com.musiccomponent.view;

import android.app.Activity;
import android.view.LayoutInflater;

import com.musiccomponent.R;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)

public class MusicAlbumViewTest {

    private MusicAlbumView albumView;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        ActivityController<Activity> activityController = Robolectric.buildActivity(Activity.class);

        albumView = (MusicAlbumView) LayoutInflater.from(activityController.get()).inflate(R.layout.music_album_view, null);
    }

    @After
    public void tearDown() {

        albumView = null;
    }

    @Test
    public void testNotNull() {

        TestCase.assertNotNull(albumView);
    }

    @Test
    public void testAlbumView() {

        albumView.setAlbumView("Metallica", "Nothing else matter",null);

        TestCase.assertEquals(albumView.tvArtist.getText(), "Metallica");
    }
}
