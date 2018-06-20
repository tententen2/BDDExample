package com.jirawat.bddexample.presentation.login

import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.jirawat.bddexample.R
import com.jirawat.bddexample.baseclass.BaseActivity
import com.jirawat.bddexample.baseclass.extension.getContentView
import com.jirawat.bddexample.baseclass.extension.observe
import com.jirawat.bddexample.baseclass.viewslice.BaseStateSwitcher
import com.jirawat.bddexample.baseclass.viewslice.StateViewSlice
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.network.HttpProvider
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCaseImpl
import com.jirawat.bddexample.presentation.login.model.NetworkState
import com.jirawat.bddexample.presentation.login.repository.PagingRepository
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModel
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModelFactory
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSlice
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSliceImpl
import kotlinx.android.synthetic.main.activity_main.*

class TestActivity(override val layoutResourceId: Int = R.layout.activity_main) :BaseActivity(){

    lateinit var livemodel:ListViewModel
    lateinit var stateSwitch: StateViewSlice
    lateinit var viewMain:ListViewSlice
    lateinit var interact: FetchMemesUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setUpviewModelObserve()
        setUpViewSlice()
        livemodel.fetchMemes(INIT_LIST)
    }

    private fun init() {
        val linearLayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        interact = FetchMemesUseCaseImpl()
        var api = HttpProvider.get().movieService()
        var repo = PagingRepository(interact,api)
        livemodel = ViewModelProviders.of(this,ListViewModelFactory(interact,repo)).get(ListViewModel::class.java)
        stateSwitch = BaseStateSwitcher()

        viewMain = ListViewSliceImpl(linearLayoutManager){
            Toast.makeText(this,"retry",Toast.LENGTH_LONG).show()
        }
        stateSwitch.init(lifecycle,getContentView())
        viewMain.init(lifecycle,getContentView())
    }

    private fun setUpViewSlice() {
        swipe_refresh.setOnRefreshListener {
            livemodel.refresh()
            viewMain.reset()
        }
    }

    private fun setUpviewModelObserve() {
        observe(livemodel.getRefreshState()){
            swipe_refresh.isRefreshing = it == NetworkState.Loading
        }
        observe(livemodel.getListData()){
            viewMain.showMemes(it)
        }
        observe(livemodel.getNetworkState()){
            when(it){
                NetworkState.Loading -> { stateSwitch.showLoading() }
                is NetworkState.LoadFail -> { stateSwitch.showError() }
                NetworkState.Loaded -> { stateSwitch.showContent() }
                NetworkState.LoadMore,NetworkState.LoadError -> { viewMain.showNetworkState(it)}
            }
        }
    }
}