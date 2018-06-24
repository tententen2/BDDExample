package com.jirawat.bddexample.mock

import android.util.Log
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.repository.ServiceMain
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls
import java.io.IOException

class FakeApi:ServiceMain {

    private var arrayFake:ArrayList<Result> = arrayListOf()
    var failmsg:String? = null

    override fun getMovieList(page: String, apiKey: String): Call<ResponseMainActivity> {
        failmsg?.let {
            return Calls.failure(IOException(it))
        }
        var response = ResponseMainActivity()
        response.results = arrayFake
        return Calls.response(response)
    }

    fun addPost(result:Result) {
        arrayFake.add(result)
    }
}