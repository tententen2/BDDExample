package com.jirawat.bddexample.firestpage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiSelector
import com.jirawat.bddexample.R
import com.jirawat.bddexample.presentation.login.TestActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PullToRefresh: BaseUITest<TestActivity>(TestActivity::class.java) {

    @Test
    fun recycler_pullRefresh_showSwipeRefreshLayout(){
        var recyclerView = device.findObject(UiSelector().resourceId("$packageName:id/memes_recycler_view")).waitForExists(20000)
        assertEquals(recyclerView,true)
        onView(withId(R.id.swipe_refresh)).perform(RecyclerViewMatcher.withCustomConstraints(swipeDown(), isDisplayingAtLeast(10)))
    }

    @Test
    fun recycler_pullRefresh_showloading(){
        var recyclerView = device.findObject(UiSelector().resourceId("$packageName:id/memes_recycler_view")).waitForExists(20000)
        assertEquals(recyclerView,true)
        scrollToLoadMore()
    }


    fun scrollToLoadMore(){
        onView(withId(R.id.memes_recycler_view)).perform(RecyclerViewActions.scrollToHolder(RecyclerViewMatcher.atPositionViewHolder()))
        onView(withId(R.id.state_progress)).check(matches(isDisplayed()))
        var recyclerView = device.findObject(UiSelector().resourceId("$packageName:id/state_progress")).waitUntilGone(10000)
        assertEquals(true,recyclerView)
    }

}