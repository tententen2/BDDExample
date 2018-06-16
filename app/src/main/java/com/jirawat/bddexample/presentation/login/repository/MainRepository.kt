package com.jirawat.bddexample.presentation.login.repository

import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.network.HttpProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {



    fun getMovieList(page:String,success:(ResponseMainActivity) -> Unit,fail:(String) -> Unit){
        HttpProvider.get().movieService().getMovieList(page).enqueue(object : Callback<ResponseMainActivity>{
            override fun onResponse(call: Call<ResponseMainActivity>?, response: Response<ResponseMainActivity>?) {
                if(response?.code() == 200)
                success.invoke(response!!.body()!!)
                else
                fail.invoke(response?.errorBody()?.string() ?: "")
            }

            override fun onFailure(call: Call<ResponseMainActivity>?, t: Throwable?) {
                fail.invoke(t?.localizedMessage!!)
            }
        })
    }
}