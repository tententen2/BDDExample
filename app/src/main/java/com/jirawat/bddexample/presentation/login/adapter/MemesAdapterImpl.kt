package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jirawat.bddexample.R
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.model.Meme
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSlice
import javax.inject.Inject

class MemesAdapterImpl :PagedListAdapter<Result,MemeViewHolder>(diffCallback) {

    private var memes: MutableList<Result> = mutableListOf()
//    private val diffCallback:DiffCallback = DiffCallback()
    private val actionLiveData: MutableLiveData<ListViewSlice.Action> = MutableLiveData()

    override fun getItemViewType(position: Int) = R.layout.view_holder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder, parent, false)
        return MemeViewHolder(itemView, actionLiveData)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        Log.d("okhttp", "holder $position")
        holder.bind(getItem(position)!!)
    }

    companion object {
        var diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result?, newItem: Result?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(oldItem: Result?, newItem: Result?): Boolean {
                return oldItem == newItem
            }

        }
    }
}
