package com.jirawat.bddexample.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.jirawat.bddexample.presentation.login.TestActivity
import com.jirawat.bddexample.presentation.login.Testqq

class dwdw{
    fun printG(){
        test<TestActivity> {
            Log.d("kdskaokdosa","GG")
        }
    }
}

inline fun  <reified T> test(callback:() -> Unit){
    callback()
}