package com.daniyalxdubizzle.androidtakehomeproject.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
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


    /* Check on App Open Main Activity Should Open as Home Page with Toolbar */
    @Test
    fun checkHomeTitle_IsDisplayed(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    /* Check On Start Shimmer Should Show */
    @Test
    fun checkHomeLoadingShimmer_IsDisplayed(){
        onView(withId(R.id.shimmer_view_container)).check(matches(isDisplayed()))
    }

    /* Check Once the Api Fetched Data Shimmer Should Hide */
    @Test
    fun checkHomeLoadingShimmer_IsGone(){

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.shimmer_view_container)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    /* Check RecyclerView is Attached to the View */
    @Test
    fun checkRecyclerView_isAttached(){

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.RV_item)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /* Check RecyclerView On Item Click Listener Open the Detail Fragment */
    @Test
    fun navDetailScreen_fromMainActivityTest(){

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.RV_item)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            clickOnViewChild(R.id.CV_ITEM)))

        onView(withId(R.id.BTN_OrderNow)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    /* Check on Back Pressed Pop the Fragment and Resume Main Activity */
    @Test
    fun navMainScreen_fromDetailScreenTest(){

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.RV_item)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            clickOnViewChild(R.id.CV_ITEM)))

        onView(withId(R.id.BTN_OrderNow)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        pressBack()

        onView(withId(R.id.RV_item)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    /* Helping Func for RecyclerView Click */
    private fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

    /* Helping Func to Add Delay for Api Response*/
    private fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

}