package com.jirawat.bddexample.presentation.login.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.adapter.DataSourceFactory
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.model.NetworkState
import java.util.*
import java.util.concurrent.Executors

class PagingRepository(private val fetchMemesUseCase: FetchMemesUseCase, private val api:ServiceMain):TestRepository {
    override fun postsOfList(): Listing<Result> {
        val factory = DataSourceFactory(fetchMemesUseCase,api)
//        val config = PagedList.Config.Builder()
//                .setPageSize(5)
//                .setEnablePlaceholders(false)
//                .build()
        val pagedList = LivePagedListBuilder(factory,30).setFetchExecutor(Executors.newSingleThreadExecutor()).build()

        val refreshState = Transformations.switchMap(factory.sourceLiveData) {
            it.initialLoad
        }

        return Listing(
                pagedList, Transformations.switchMap(factory.sourceLiveData){ it.networkState },refreshState,{ factory.sourceLiveData.value?.invalidate() },{}
        )
    }
}