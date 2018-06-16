package com.jirawat.bddexample.data.network

import com.jirawat.bddexample.presentation.login.repository.ServiceMain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpProvider {
    companion object {
        var instance: HttpProvider? = null

        fun get(): HttpProvider {
            if (instance == null) {
                instance = HttpProvider()
            }
            return instance!!
        }
    }

    fun movieService() = retrofit("https://api.themoviedb.org/3/").create(ServiceMain::class.java)

    private fun retrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonProvider.get()))
                .client(httpClient())
                .build()
    }

    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
//                .addInterceptor(HttpLogInterceptor())
                .addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()
    }
}