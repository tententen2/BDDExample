package com.jirawat.bddexample.secpage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiSelector
import android.support.v7.widget.RecyclerView
import com.jirawat.bddexample.R
import com.jirawat.bddexample.firestpage.BaseUITest
import com.jirawat.bddexample.firestpage.RecyclerViewMatcher
import com.jirawat.bddexample.presentation.login.SecActivity
import com.jirawat.bddexample.presentation.login.TestActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecTest:BaseUITest<TestActivity>(TestActivity::class.java) {

    @Test
    fun recycler_clickToSec_showSec(){
        var recyclerView = device.findObject(UiSelector().resourceId("$packageName:id/memes_recycler_view")).waitForExists(20000)
        Assert.assertEquals(recyclerView, true)
        onView(withId(R.id.memes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,RecyclerViewMatcher.clickChildViewWithId(R.id.viewHolder_item)))
        Intents.intended(IntentMatchers.hasComponent(SecActivity::class.java.name))
        SecActivityTest().textview_dataapi_show()
        pressBack()
        onView(withId(R.id.memes_recycler_view)).check(matches(isDisplayed()))
    }

}