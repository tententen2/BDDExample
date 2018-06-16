package com.jirawat.bddexample.data.network

import com.jirawat.bddexample.data.model.MemesDto
import io.reactivex.Single
import retrofit2.http.GET
import java.util.*

interface ImgFlipApi {

    @GET("get_memes")
    fun fetchMemes(): Single<MemesDto>
}
