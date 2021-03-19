package com.daniyalxdubizzle.androidtakehomeproject.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.daniyalxdubizzle.androidtakehomeproject.R
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkHomeTitle_IsDisplayed(){

        //val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHomeLoadingShimmer_IsDisplayed(){

       // val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.shimmer_view_container)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHomeLoadingShimmer_IsGone(){

       // val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.shimmer_view_container)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

    ///// Test Navigation on Item Click

    @Test
    fun navDetailScreenTest(){

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.RV_item)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            clickOnViewChild(R.id.CV_ITEM)))

        onView(withId(R.id.BTN_OrderNow)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

}