package com.jirawat.bddexample.presentation.login.domain

import android.util.Log
import com.jirawat.bddexample.baseclass.domain.BaseUseCase
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.presentation.login.repository.MainRepository
import retrofit2.Response

class FetchMemesUseCaseImpl : BaseUseCase<FetchMemesUseCase.Result>(), FetchMemesUseCase {
    override fun checkInput(input: String) {
//        liveData.value = FetchMemesUseCase.Result.OnError("error ja")
    }

    override fun execute(page:String,success:(ResponseMainActivity) -> Unit,error:(String) -> Unit) {
        MainRepository.getMovieList(page, {
            liveData.value = FetchMemesUseCase.Result.OnSuccess
            success.invoke(it)
        }, {
            liveData.value = FetchMemesUseCase.Result.OnError(it)
            error.invoke(it)
        })
    }

}
