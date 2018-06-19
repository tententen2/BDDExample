package com.jirawat.bddexample.presentation.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.Transformations.switchMap
import android.arch.paging.PagedList
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.model.NetworkState
import com.jirawat.bddexample.presentation.login.repository.TestRepository
import java.util.*

class ListViewModelImpl(private val state: MediatorLiveData<State>,private val fetchMemesUseCase: FetchMemesUseCase,private val repo: TestRepository) : ListViewModel() {


    private val subredditName = MutableLiveData<String>()
    private val repoResult = Transformations.map(subredditName, {
        repo.postsOfList()
    })
    val pageListTest = switchMap(repoResult){ it.pagedList }
    val networkStateModel = switchMap(repoResult){ it.networkState }
    val refreshStateModel = switchMap(repoResult){ it.refreshState }

    init {
        state.addSource(fetchMemesUseCase.getLiveData(), ::onFetchMemesResult)
    }

    override fun getListData(): LiveData<PagedList<Result>> = pageListTest

    override fun onCleared() {
        fetchMemesUseCase.cleanUp()


    }
    override fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    override fun getState(): LiveData<State> = state

    override fun getNetworkState(): LiveData<NetworkState> = networkStateModel

    override fun getRefreshState(): LiveData<NetworkState> = refreshStateModel

    override fun fetchMemes(init: String) {
        if(subredditName.value != init){
            subredditName.value = init
        }
    }

    private fun onFetchMemesResult(result: FetchMemesUseCase.Result?) {
        when (result) {
            is FetchMemesUseCase.Result.OnSuccess -> {
                state.value = State.ShowContent
            }
            is FetchMemesUseCase.Result.OnError -> state.value = State.ShowError(result.error)
        }
    }
}