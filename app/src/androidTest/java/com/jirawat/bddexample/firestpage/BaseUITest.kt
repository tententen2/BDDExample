package com.jirawat.bddexample.firestpage

import android.app.Activity
import android.app.Instrumentation
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

open class BaseUITest<T : Activity> (kclass : Class<T>){
    lateinit var instr: Instrumentation
    lateinit var device: UiDevice
    lateinit var packageName:String

    @get:Rule
    var mActivity = IntentsTestRule(kclass)

    @Before
    fun init(){
        instr = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instr)
        packageName = device.currentPackageName
    }

}

