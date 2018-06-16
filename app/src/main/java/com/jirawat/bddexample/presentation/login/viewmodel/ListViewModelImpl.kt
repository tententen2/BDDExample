package com.jirawat.bddexample.presentation.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.adapter.DataSourceFactory
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCaseImpl

class ListViewModelImpl(private val state: MediatorLiveData<State>,private val fetchMemesUseCase: FetchMemesUseCase) : ListViewModel() {

    lateinit var listRawData:LiveData<PagedList<Result>>

    init {
        state.addSource(fetchMemesUseCase.getLiveData(), ::onFetchMemesResult)
        state.value = State.ShowLoading
        val config = PagedList.Config.Builder()
                .setPageSize(10)
//                .setInitialLoadSizeHint(10)
                .setEnablePlaceholders(false)
                .build()

        listRawData = LivePagedListBuilder<Int,Result>(DataSourceFactory(fetchMemesUseCase),config).build()
    }

    override fun getListData(): LiveData<PagedList<Result>> = listRawData

    override fun onCleared() {
        fetchMemesUseCase.cleanUp()
    }

    override fun getState(): LiveData<State> = state

    override fun fetchMemes() {
//        state.value = State.ShowLoading
//        fetchMemesUseCase.execute()
    }

    private fun onFetchMemesResult(result: FetchMemesUseCase.Result?) {
        when (result) {
            is FetchMemesUseCase.Result.OnSuccess -> {
//                state.value = State.MemesLoaded(result.memes)
                state.value = State.ShowContent
            }
            is FetchMemesUseCase.Result.OnError -> state.value = State.ShowError(result.error)
        }
    }
}