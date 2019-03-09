package com.jirawat.bddexample.presentation.login.viewslice

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.paging.PagedList
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.jirawat.bddexample.baseclass.viewslice.BaseViewSlice
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.presentation.login.adapter.MemesAdapterImpl
import com.jirawat.bddexample.presentation.login.model.NetworkState
import kotlinx.android.synthetic.main.activity_main.*

class ListViewSliceImpl(private val layoutManager:LinearLayoutManager,private val retryCallback:() -> Unit): BaseViewSlice(),ListViewSlice {
    override fun reset() {
        memes_recycler_view.scrollToPosition(0)
//        (memes_recycler_view.adapter as? MemesAdapterImpl)?.submitList(null)
    }


    lateinit var adapter:MemesAdapterImpl
    lateinit var actionLiveData:MutableLiveData<ListViewSlice.Action>

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun oncreate(){
        init()
        setUpRecyclerView()
    }

    fun init(){
        adapter = MemesAdapterImpl(retryCallback)
        actionLiveData = MutableLiveData()
    }

    private fun setUpRecyclerView() {
        memes_recycler_view.layoutManager = layoutManager
        memes_recycler_view.adapter = adapter
    }

    override fun getAction(): LiveData<ListViewSlice.Action> = actionLiveData

    override fun showMemes(memes: PagedList<Result>) {
        adapter.submitList(memes)
    }

    override fun showNetworkState(state: NetworkState) {
//        adapter.setNetworkState(state)
    }
}
