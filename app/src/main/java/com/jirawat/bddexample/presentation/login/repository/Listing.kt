package com.jirawat.bddexample.presentation.login.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import java.util.*

data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<Objects>,
        val refreshState: LiveData<Objects>,
        val refresh:() -> Unit,
        val retry: () -> Unit
)