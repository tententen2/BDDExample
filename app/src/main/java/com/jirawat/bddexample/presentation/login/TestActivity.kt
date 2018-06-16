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
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCaseImpl
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
        setUpViewSlice()
        setUpviewModelObserve()
        livemodel.fetchMemes()
    }

    private fun init() {
        val linearLayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        interact = FetchMemesUseCaseImpl()
        livemodel = ViewModelProviders.of(this,ListViewModelFactory(interact)).get(ListViewModel::class.java)
        stateSwitch = BaseStateSwitcher()

        viewMain = ListViewSliceImpl(linearLayoutManager)

        stateSwitch.init(lifecycle,getContentView())
        viewMain.init(lifecycle,getContentView())
    }

    private fun setUpViewSlice() {

    }

    private fun setUpviewModelObserve() {
        observe(livemodel.getState()){ onStateChanged(it) }
        observe(livemodel.getListData()){ viewMain.showMemes(it)
            interact.checkInput("")
        }
    }

    private fun onStateChanged(state: ListViewModel.State) {
        when(state){
            ListViewModel.State.ShowLoading -> stateSwitch.showLoading()
            ListViewModel.State.ShowContent -> stateSwitch.showContent()
            is ListViewModel.State.ShowError -> {
                stateSwitch.showError()
                Toast.makeText(this,state.error,Toast.LENGTH_LONG).show()
            }
        }

    }
}