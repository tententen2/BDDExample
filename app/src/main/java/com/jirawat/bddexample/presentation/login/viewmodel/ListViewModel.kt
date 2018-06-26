package com.jirawat.bddexample.presentation.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.model.InputCheck
import com.jirawat.bddexample.presentation.login.model.NetworkState
import com.jirawat.bddexample.presentation.login.state.ErrorTextState
import java.util.*

abstract class ListViewModel : ViewModel() {

    abstract sealed class State {
        data class MemesLoaded(val memes: PagedList<Result>) : State()
        object ShowLoading : State()
        object ShowContent : State()
        data class ShowError(val error:String) : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchMemes(init:String): Boolean

    abstract fun getListData() : LiveData<PagedList<Result>>

    abstract fun getNetworkState(): LiveData<NetworkState>

    abstract fun getRefreshState() : LiveData<NetworkState>

    abstract fun getInputState() : LiveData<ErrorTextState>

    abstract fun getInput() : InputCheck

    abstract fun refresh()

    abstract fun doCheckInput()


}