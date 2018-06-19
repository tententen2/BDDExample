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

class TestDataSource(private val fetchMemesUseCase: FetchMemesUseCase,private val api:ServiceMain):PageKeyedDataSource<Int, Result>() {
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        Log.d("dksaokdosa ","loadInitial "+Thread.currentThread())
//        networkState.postValue(NetworkState.Loading)
//        try{
//            val request = api.getMovieList("1")
//            val response = request.execute()
//            networkState.postValue(NetworkState.Loaded)
//            initialLoad.postValue(NetworkState.Loaded)
//            callback.onResult(response?.body()?.results!!,1,(response.body()?.page ?: 1)+1)
//        }catch (e:Exception){
//
//        }
        api.getMovieList("1").enqueue(object : Callback<ResponseMainActivity>{
            override fun onFailure(call: Call<ResponseMainActivity>?, t: Throwable?) {
                networkState.postValue(NetworkState.LoadFail(t?.localizedMessage ?: ""))
            }

            override fun onResponse(call: Call<ResponseMainActivity>?, response: Response<ResponseMainActivity>?) {
                callback.onResult(response?.body()?.results!!,0,(response.body()?.page ?: 1)+1)
                networkState.postValue(NetworkState.Loaded)
                Log.d("dksaokdosa ","onResponse "+Thread.currentThread())

            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
//        networkState.postValue(NetworkState.LoadMore)

//        try{
//            val request = api.getMovieList(params.key.toString())
//            val response = request.execute()
//            networkState.postValue(NetworkState.Loaded)
//            callback.onResult(response?.body()?.results!!,(response.body()?.page ?: 1)+1)
//        }catch (e:Exception){
//
//        }

        Log.d("dskoakdosa pageafter",""+params.key)
        api.getMovieList(params.key.toString()).enqueue(object : Callback<ResponseMainActivity>{
            override fun onFailure(call: Call<ResponseMainActivity>?, t: Throwable?) {
                networkState.postValue(NetworkState.LoadError)
            }

            override fun onResponse(call: Call<ResponseMainActivity>?, response: Response<ResponseMainActivity>?) {
                initialLoad.postValue(NetworkState.Loaded)
                networkState.postValue(NetworkState.Loaded)
                callback.onResult(response?.body()?.results!!,(response.body()?.page ?: 1)+1)
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
    }
}