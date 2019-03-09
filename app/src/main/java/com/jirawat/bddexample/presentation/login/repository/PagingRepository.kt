package com.jirawat.bddexample.presentation.login.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.adapter.DataSourceFactory
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import java.util.concurrent.Executors

class PagingRepository(private val fetchMemesUseCase: FetchMemesUseCase, private val api:ServiceMain):TestRepository {

    @MainThread
    override fun postsOfList(): Listing<Result> {
        val factory = DataSourceFactory(fetchMemesUseCase,api)
        val pagedList = LivePagedListBuilder(factory,30).setFetchExecutor(Executors.newFixedThreadPool(5)).build()
        val refreshState = Transformations.switchMap(factory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
                pagedList, Transformations.switchMap(factory.sourceLiveData){ it.networkState },refreshState,{ factory.sourceLiveData.value?.invalidate() },{ factory.sourceLiveData.value?.retryAllFailed() }
        )
    }
}