package com.jirawat.bddexample.presentation.login.adapter

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.network.HttpProvider
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.repository.ServiceMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestDataSource(private val fetchMemesUseCase: FetchMemesUseCase,private val api:ServiceMain):PageKeyedDataSource<Int, Result>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {

        api.getMovieList("1").enqueue(object : Callback<ResponseMainActivity>{
            override fun onFailure(call: Call<ResponseMainActivity>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ResponseMainActivity>?, response: Response<ResponseMainActivity>?) {
                callback.onResult(response?.body()?.results!!,0,((response?.body()?.page ?: 1)+1))
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        api.getMovieList(params.key.toString()).enqueue(object : Callback<ResponseMainActivity>{
            override fun onFailure(call: Call<ResponseMainActivity>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ResponseMainActivity>?, response: Response<ResponseMainActivity>?) {
                callback.onResult(response?.body()?.results!!,((response?.body()?.page ?: 1)+1))
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
    }
}