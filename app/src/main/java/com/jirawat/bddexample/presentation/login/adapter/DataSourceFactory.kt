package com.jirawat.bddexample.presentation.login.adapter

import android.arch.paging.DataSource
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase

class DataSourceFactory(private val fetchMemesUseCase: FetchMemesUseCase):DataSource.Factory<Int,Result>() {
    override fun create(): DataSource<Int, Result> {
        var datasource = com.jirawat.bddexample.presentation.login.adapter.DataSource(fetchMemesUseCase)
        return datasource
    }
}