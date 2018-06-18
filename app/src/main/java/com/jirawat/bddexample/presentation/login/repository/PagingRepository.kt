package com.jirawat.bddexample.presentation.login.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.adapter.DataSourceFactory
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import java.util.*

class PagingRepository(private val fetchMemesUseCase: FetchMemesUseCase, private val api:ServiceMain):TestRepository {
    override fun postsOfList(): Listing<Result> {
        val factory = DataSourceFactory(fetchMemesUseCase,api)
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .build()

        val pagedList = LivePagedListBuilder(factory,config).build()
        return Listing(
                pagedList,MutableLiveData<Objects>(),MutableLiveData<Objects>(),{},{}
        )
    }
}