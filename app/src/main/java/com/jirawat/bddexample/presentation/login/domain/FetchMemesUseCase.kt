package com.jirawat.bddexample.presentation.login.domain

import com.jirawat.bddexample.baseclass.domain.UseCase
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.model.Meme

interface FetchMemesUseCase : UseCase<FetchMemesUseCase.Result> {

    sealed class Result {
        object OnSuccess : Result()
        data class OnError(val error:String) : Result()
    }

    fun execute(page:String,success:(ResponseMainActivity) -> Unit,error:(String) -> Unit)

    fun checkInput(input:String)
}
