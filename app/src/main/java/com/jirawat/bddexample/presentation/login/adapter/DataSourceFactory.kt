package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.repository.ServiceMain

class DataSourceFactory(private val fetchMemesUseCase: FetchMemesUseCase,val api:ServiceMain):DataSource.Factory<Int,Result>() {
    val sourceLiveData = MutableLiveData<TestDataSource>()
    override fun create(): DataSource<Int, Result> {
        var datasource = TestDataSource(fetchMemesUseCase,api)
        sourceLiveData.postValue(datasource)
        return datasource
    }
}