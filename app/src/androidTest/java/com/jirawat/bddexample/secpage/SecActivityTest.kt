package com.jirawat.bddexample.secpage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.jirawat.bddexample.R


class SecActivityTest {

    fun textview_dataapi_show(){
        onView(withId(R.id.title_sec)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_sec)).check(matches(isDisplayed()))
    }
}