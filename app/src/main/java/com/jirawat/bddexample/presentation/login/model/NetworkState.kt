package com.jirawat.bddexample.presentation.login.model

sealed class NetworkState{
        object Loading : NetworkState()
        object Loaded : NetworkState()
        object LoadMore : NetworkState()
        object LoadError : NetworkState()
        data class Fail(var error:String) : NetworkState()
}