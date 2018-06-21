package com.jirawat.bddexample.mock

import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.presentation.login.repository.ServiceMain
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls

class FakeApi:ServiceMain {
    override fun getMovieList(page: String, apiKey: String): Call<ResponseMainActivity> {
        var response = ResponseMainActivity()
        response.page = page.toInt()
        return Calls.response(Response.success(ResponseMainActivity()))
    }
}