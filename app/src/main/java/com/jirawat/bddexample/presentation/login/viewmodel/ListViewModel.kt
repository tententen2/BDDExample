package com.jirawat.bddexample.presentation.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.jirawat.bddexample.data.MainActivity.Result

abstract class ListViewModel : ViewModel() {

    abstract sealed class State {
        data class MemesLoaded(val memes: PagedList<Result>) : State()
        object ShowLoading : State()
        object ShowContent : State()
        data class ShowError(val error:String) : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchMemes()

    abstract fun getListData() : LiveData<PagedList<Result>>
}