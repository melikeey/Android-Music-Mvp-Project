package com.melikeey.base;

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
public class FragmentNavigatorTest extends BaseUnitTest {

    @Mock
    private BaseNavigator baseNavigator;

    private UnitTestFragment testFragment = UnitTestFragment.newInstance();

    private UnitTestActivity activity;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        activity = (UnitTestActivity) startFragment(testFragment);

        baseNavigator = new BaseNavigator(activity);
    }

    @After
    public void tearDown() throws Exception {

        baseNavigator = null;
    }

    @Test
    public void addFragmentTest(){

        baseNavigator.openFragment(UnitTestFragment.newInstance(), BaseNavigator.Transaction.ADD, true);

        TestCase.assertEquals(UnitTestFragment.TAG, getLastFragmentName(testFragment));
    }

    @Test
    public void replaceFragmentTest(){

        baseNavigator.openFragment(UnitTestFragment.newInstance(), BaseNavigator.Transaction.REPLACE, true);

        TestCase.assertEquals(UnitTestFragment.TAG, getLastFragmentName(testFragment));
    }

    @Test
    public void popBackStackFragmentTest(){

        baseNavigator.openFragment(UnitTestFragment.newInstance(), BaseNavigator.Transaction.REPLACE,  true);
        baseNavigator.popBackStack();

        TestCase.assertEquals(UnitTestFragment.TAG, activity.getSupportFragmentManager().getFragments().get(0).getClass().getSimpleName());
    }

    @Test
    public void popBackStackImmediateFragmentTest(){

        baseNavigator.openFragment(UnitTestFragment.newInstance(), BaseNavigator.Transaction.REPLACE, true);
        baseNavigator.popBackStackImmediate();

        TestCase.assertEquals(activity.getSupportFragmentManager().getBackStackEntryCount(), 0);
    }
}