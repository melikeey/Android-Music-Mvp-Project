package com.melikeey.base;

import android.view.View;

import com.melikeey.ui.base.BaseUnitTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(sdk = 28)

@RunWith(RobolectricTestRunner.class)
public class FragmentBaseNavigatorTest extends BaseUnitTest {

    @Mock
    private BaseNavigator baseNavigator;

    private TestFragmentFirst testFragment = TestFragmentFirst.newInstance();

    private TestActivity activity;

    @Mock
    View view;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        activity = (TestActivity) startFragment(testFragment);

        baseNavigator = new BaseNavigator(activity);
    }

    @After
    public void tearDown() throws Exception {

        baseNavigator = null;
    }

    @Test
    @Override
    public void setUpTest() {

        testFragment.setUp(view);
    }

    @Test
    public void addFragmentTest(){

        baseNavigator.openFragment(TestFragmentFirst.newInstance(), BaseNavigator.Transaction.ADD, true);

        TestCase.assertEquals(TestFragmentFirst.TAG, getLastFragmentName(testFragment));
    }

    @Test
    public void replaceFragmentTest(){

        baseNavigator.openFragment(TestFragmentFirst.newInstance(), BaseNavigator.Transaction.REPLACE, true);

        TestCase.assertEquals(TestFragmentFirst.TAG, getLastFragmentName(testFragment));
    }

    @Test
    public void popBackStackFragmentTest(){

        baseNavigator.openFragment(TestFragmentFirst.newInstance(), BaseNavigator.Transaction.REPLACE,  true);
        baseNavigator.popBackStack();

        TestCase.assertEquals(TestFragmentFirst.TAG, activity.getSupportFragmentManager().getFragments().get(0).getClass().getSimpleName());
    }

    @Test
    public void popBackStackImmediateFragmentTest(){

        baseNavigator.openFragment(TestFragmentFirst.newInstance(), BaseNavigator.Transaction.REPLACE, true);
        baseNavigator.popBackStackImmediate();

        TestCase.assertEquals(activity.getSupportFragmentManager().getBackStackEntryCount(), 0);
    }
}