package com.jirawat.bddexample.baseclass.viewslice


interface StateViewSlice : ViewSlice {

    fun showLoading()

    fun showContent()

    fun showError()
}
