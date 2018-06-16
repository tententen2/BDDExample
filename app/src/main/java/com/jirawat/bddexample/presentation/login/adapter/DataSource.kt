package com.jirawat.bddexample.presentation.login.adapter

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.network.HttpProvider
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSource(private val fetchMemesUseCase: FetchMemesUseCase):PageKeyedDataSource<Int, Result>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        fetchMemesUseCase.execute("1",{
           callback.onResult(it.results,0,((it.page ?: 1)+1))
        },{

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        fetchMemesUseCase.execute(params.key.toString(),{
            callback.onResult(it.results,((it.page ?: 1)+1))
        },{

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
    }
}