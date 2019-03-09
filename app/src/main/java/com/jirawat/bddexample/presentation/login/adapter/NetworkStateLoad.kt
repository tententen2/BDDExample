package com.jirawat.bddexample.presentation.login.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jirawat.bddexample.R
import com.jirawat.bddexample.presentation.login.model.NetworkState
import kotlinx.android.synthetic.main.network_state.view.*

class NetworkStateLoad(view:View,private val retryCallback:() -> Unit): RecyclerView.ViewHolder(view) {
    private val fillper = view.network_state_flipper

    fun bintTo(networkState: NetworkState?){
        if(networkState == NetworkState.LoadMore){
            fillper.displayedChild = 0
        }else if(networkState == NetworkState.LoadError){
            val button = fillper.retry_button
            fillper.displayedChild = 1
            button.setOnClickListener {
                retryCallback.invoke()
            }

        }
    }

    companion object {
        fun create(parent:ViewGroup,retryCallback:() -> Unit):NetworkStateLoad{
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.network_state, parent, false)
            return NetworkStateLoad(itemView,retryCallback)
        }
    }
}