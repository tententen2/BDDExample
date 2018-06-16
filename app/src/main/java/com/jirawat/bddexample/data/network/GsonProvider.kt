package com.jirawat.bddexample.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonProvider {

    companion object {
        var instance: Gson? = null

        fun get(): Gson {
            if(instance == null){
                instance = GsonBuilder().setPrettyPrinting().create()
            }
            return instance!!
        }
    }
}