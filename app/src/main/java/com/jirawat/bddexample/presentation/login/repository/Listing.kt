package com.jirawat.bddexample.presentation.login.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.jirawat.bddexample.presentation.login.model.NetworkState
import java.util.*

data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkState>,
        val refreshState: LiveData<NetworkState>,
        val refresh:() -> Unit,
        val retry: () -> Unit
)