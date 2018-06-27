package com.jirawat.bddexample

import android.app.Instrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasErrorText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import com.jirawat.bddexample.presentation.login.TestActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.InstrumentationRegistry
import junit.framework.Assert
import org.junit.Before


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    lateinit var instr:Instrumentation
    lateinit var device:UiDevice
    lateinit var packageName:String

    @get:Rule
    var mActivity = ActivityTestRule<TestActivity>(TestActivity::class.java)

    @Before
    fun init(){
        instr = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instr)
        packageName = device.currentPackageName
    }

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




