package com.jirawat.bddexample.presentation.login.repository

import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceMain {

    @GET("discover/movie")
    fun getMovieList(@Query("page") page:String
                     , @Query("api_key") apiKey:String="75d7ca020a71b63e0c61c7d21588da97"):Call<ResponseMainActivity>

}