package com.jirawat.bddexample.presentation.login.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCaseImpl
import com.jirawat.bddexample.presentation.login.repository.TestRepository
import javax.inject.Inject


class ListViewModelFactory(private val interact:FetchMemesUseCase,private val repo:TestRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return ListViewModelImpl(MediatorLiveData(),interact,repo) as T
    }
}