package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jirawat.bddexample.R
import com.jirawat.bddexample.baseclass.adapter.BaseViewHolder
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.model.Meme
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSlice
import kotlinx.android.synthetic.main.view_holder.*
import kotlinx.android.synthetic.main.view_holder.view.*


class MemeViewHolder(
        view: View
) : RecyclerView.ViewHolder(view) {
    private val name = view.nametest

    companion object {
        fun create(parent:ViewGroup):MemeViewHolder{
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder, parent, false)
            return MemeViewHolder(itemView)
        }
    }

    fun bind(data: Result) {
        setName(data.originalTitle ?: "")
//        setImage(data.imageUrl, data.imageWidth, data.imageHeight)
//        setViewClickListener(actionLiveData, data)
    }

    private fun setViewClickListener(actionLiveData: MutableLiveData<ListViewSlice.Action>, data: Meme) {
//        itemView.setOnClickListener { actionLiveData.value = ListViewSlice.Action.MemeClicked(data) }
    }

    private fun setName(name: String) {
        this.name.text = name
    }

    private fun setImage(imageUrl: String, imageWidth: Int, imageHeight: Int) {
//        val layoutParams = meme_image.layoutParams
//        layoutParams.width = imageWidth
//        layoutParams.height = imageHeight
//        meme_image.layoutParams = layoutParams
//        Picasso.with(context).load(imageUrl).into(meme_image)
    }
}
