package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.repository.ServiceMain

class DataSourceFactory(val api:ServiceMain):DataSource.Factory<String,Result>() {
    val sourceLiveData = MutableLiveData<TestDataSource>()
    override fun create(): DataSource<String, Result> {
        var datasource = TestDataSource(api)
        sourceLiveData.postValue(datasource)
        return datasource
    }
}