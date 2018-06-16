package com.jirawat.bddexample.presentation.login.adapter

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.jirawat.bddexample.baseclass.adapter.BaseViewHolder
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.model.Meme
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSlice
import kotlinx.android.synthetic.main.view_holder.*


class MemeViewHolder(
        itemView: View,private val actionLiveData: MutableLiveData<ListViewSlice.Action>
) : BaseViewHolder<Result>(itemView) {

    override fun bind(data: Result) {
        setName(data.originalTitle ?: "")
//        setImage(data.imageUrl, data.imageWidth, data.imageHeight)
//        setViewClickListener(actionLiveData, data)
    }

    private fun setViewClickListener(actionLiveData: MutableLiveData<ListViewSlice.Action>, data: Meme) {
//        itemView.setOnClickListener { actionLiveData.value = ListViewSlice.Action.MemeClicked(data) }
    }

    private fun setName(name: String) {
        nametest.text = name
    }

    private fun setImage(imageUrl: String, imageWidth: Int, imageHeight: Int) {
//        val layoutParams = meme_image.layoutParams
//        layoutParams.width = imageWidth
//        layoutParams.height = imageHeight
//        meme_image.layoutParams = layoutParams
//        Picasso.with(context).load(imageUrl).into(meme_image)
    }
}
