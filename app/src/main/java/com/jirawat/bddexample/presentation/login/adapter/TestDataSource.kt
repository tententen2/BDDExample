package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.network.HttpProvider
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.model.NetworkState
import com.jirawat.bddexample.presentation.login.repository.ServiceMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestDataSource(private val api: ServiceMain) : PageKeyedDataSource<String, Result>() {
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    private var retry: (() -> Any)? = null


    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Result>) {
        networkState.postValue(NetworkState.Loading)
        try {
            val request = api.getMovieList("1")
            val response = request.execute()
            retry = null
            networkState.postValue(NetworkState.Loaded)
            initialLoad.postValue(NetworkState.Loaded)
            callback.onResult(response?.body()?.results!!, null, "2")
        } catch (e: Exception) {

        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
        networkState.postValue(NetworkState.LoadMore)

        api.getMovieList(params.key.toString()).enqueue(object : Callback<ResponseMainActivity> {
            override fun onFailure(call: Call<ResponseMainActivity>?, t: Throwable?) {
                networkState.postValue(NetworkState.LoadError)
            }

            override fun onResponse(call: Call<ResponseMainActivity>?, response: Response<ResponseMainActivity>?) {

                retry = null
                initialLoad.postValue(NetworkState.Loaded)
                networkState.postValue(NetworkState.Loaded)
                callback.onResult(response?.body()?.results!!, ((response.body()?.page ?: 1) + 1).toString())
            }

        })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
    }
}