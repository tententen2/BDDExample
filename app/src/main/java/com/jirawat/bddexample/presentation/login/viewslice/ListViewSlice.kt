package com.jirawat.bddexample.presentation.login.viewslice

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.jirawat.bddexample.baseclass.viewslice.ViewSlice
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.model.Meme
import com.jirawat.bddexample.presentation.login.model.NetworkState


interface ListViewSlice : ViewSlice {

    sealed class Action {
        data class MemeClicked(val meme: Meme) : Action()
    }

    fun getAction(): LiveData<Action>

    fun showMemes(memes: PagedList<Result>)

    fun showNetworkState(state:NetworkState)
}