package com.jirawat.bddexample.firestpage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasErrorText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiSelector
import org.junit.Test
import org.junit.runner.RunWith
import com.jirawat.bddexample.R
import com.jirawat.bddexample.presentation.login.TestActivity
import junit.framework.Assert


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest: BaseUITest<TestActivity>(TestActivity::class.java) {

    @Test
    fun editTextName_Empty_hasError(){
        var fountEdittext = device.findObject(UiSelector().resourceId("$packageName:id/usernameInput")).waitForExists(20000)
        Assert.assertEquals(fountEdittext,true)
        onView(withId(R.id.usernameInput)).perform(typeText(""))
        onView(withId(R.id.buttonOk)).perform(click())
        onView(withId(R.id.usernameInput)).check(matches(hasErrorText("พัง")))
    }
    @Test
    fun editTextName_NotEmpty_hasSuccess(){

        var fountEdittext = device.findObject(UiSelector().resourceId("$packageName:id/usernameInput")).waitForExists(20000)
        Assert.assertEquals(fountEdittext,true)

        onView(withId(R.id.usernameInput)).perform(typeText("dskoakdoskaodk ksdoakodksao"))
        onView(withId(R.id.buttonOk)).perform(click())
        onView(withId(R.id.passwordInput)).check(matches(hasErrorText("error")))
        onView(withId(R.id.phoneInput)).check(matches(hasErrorText("error")))
    }

}




