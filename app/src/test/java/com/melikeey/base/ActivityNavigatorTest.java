package com.melikeey.base;

import com.melikeey.ui.base.BaseUnitTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(sdk = 28)
@RunWith(RobolectricTestRunner.class)
public class ActivityNavigatorTest extends BaseUnitTest {

    private BaseNavigator baseNavigator;

    private UnitTestActivity activity;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        startActivity();

        baseNavigator = new BaseNavigator(activity);
    }

    @After
    public void tearDown() throws Exception {

        baseNavigator = null;
        activity = null;
    }

    @Test
    public void activityIsFinishingTest() {

        baseNavigator.openActivity(UnitTestActivity.class, true);
        TestCase.assertTrue(activity.isFinishing());
    }


    @Test
    public void activityIsNotFinishingTest() {

        baseNavigator.openActivity(UnitTestActivity.class, false);
        TestCase.assertFalse(activity.isFinishing());
    }

    public void startActivity(){
        activity = Robolectric.buildActivity(UnitTestActivity.class)
                .create()
                .resume()
                .get();
    }
}