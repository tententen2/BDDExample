package com.jirawat.bddexample.baseclass.viewslice

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.view.View

interface ViewSlice : LifecycleObserver {

    fun init(lifecycle: Lifecycle, view: View)
}
