package com.jirawat.bddexample.presentation.login.model

sealed class NetworkState{
        object Loading : NetworkState()
        object Loaded : NetworkState()
        data class Fail(var error:String) : NetworkState()
}