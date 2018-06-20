package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jirawat.bddexample.R
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.model.Meme
import com.jirawat.bddexample.presentation.login.model.NetworkState
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSlice
import javax.inject.Inject

class MemesAdapterImpl(private val retry:() -> Unit) :PagedListAdapter<Result,RecyclerView.ViewHolder>(diff) {

    private var netWorkState:NetworkState? = null
    private val actionLiveData: MutableLiveData<ListViewSlice.Action> = MutableLiveData()

//    override fun getItemViewType(position: Int):Int{
//        Log.d("dsakokdoskadsa"," item: "+position)
////        return if(hasExtraRow() && position == itemCount - 1){
////            R.layout.network_state
////        }else{
//            return R.layout.view_holder
////        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("kdoaskodksoa","onCreateViewHolder"+Thread.currentThread())
//        return when(viewType){
            return MemeViewHolder.create(parent)
//            R.layout.network_state -> NetworkStateLoad.create(parent,retry)
//            else -> { throw Exception()}
//        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kdoaskodksoa","onBindViewHolder "+Thread.currentThread())
//        when(getItemViewType(position)){
            return (holder as MemeViewHolder).bind(getItem(position)!!)
//            R.layout.network_state -> (holder as NetworkStateLoad).bintTo(netWorkState)
//        }
    }


    private fun hasExtraRow() = netWorkState != null && netWorkState != NetworkState.Loaded

    fun setNetworkState(netWorkState: NetworkState) { // Loading
        val privous = this.netWorkState // Loaded
        val hadExtraRow = hasExtraRow() // false
        this.netWorkState = netWorkState
        val hasExtraRow = hasExtraRow() // true
        if(hadExtraRow != hasExtraRow){ // true
            if(hadExtraRow){ // false
                notifyItemRemoved(super.getItemCount())
            }else{ // true
                notifyItemInserted(super.getItemCount())
            }
        }else if(hasExtraRow && privous != netWorkState){
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result?, newItem: Result?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(oldItem: Result?, newItem: Result?): Boolean {
                return oldItem == newItem
            }

        }
    }
}
