package com.ceduliocezar.scalablecapital.presentation.home;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ceduliocezar.scalablecapital.R;
import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Test suite for {@link GitHubRepoListFragment}
 */
// TODO: 6/18/2018 create a simple rest simulating github api with ruby (sinatra) or python ( flask ), so we don't rely on internet connection
@RunWith(AndroidJUnit4.class)
public class GitHubRepoListFragmentTest {

    @Rule
    public ReportHelper reportHelper = Factory.getReportHelper();

    @Rule
    public IntentsTestRule<HomeActivity> activityRule = new IntentsTestRule<>(
            HomeActivity.class,
            true,
            true);

    @Before
    public void setUp() {
        IdlingResource idlingResource = activityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void tearDown() throws Exception {
        reportHelper.label("stoppingApp");
    }

    @Test
    public void test_showList() {
        reportHelper.label("beginMainScreen");
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        reportHelper.label("mainScreenBeforeScroll");
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(15));
        reportHelper.label("mainScreenAfterScroll");
        onView(withText("37430328")).check(matches(isDisplayed()));
        reportHelper.label("endMainScreen");
    }
}
