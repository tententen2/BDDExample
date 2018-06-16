package com.jirawat.bddexample.baseclass.domain

import android.arch.lifecycle.LiveData

interface UseCase<T> {

    fun getLiveData(): LiveData<T>

    fun cleanUp()
}
