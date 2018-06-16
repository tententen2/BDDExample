package com.jirawat.bddexample.presentation.login.adapter

import android.support.v7.widget.RecyclerView
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.data.model.Meme

abstract class MemesAdapter : RecyclerView.Adapter<MemeViewHolder>() {

    abstract fun setMemes(memes: List<Result>)

    abstract fun addMemes(memes: List<Result>)

    abstract fun clearMemes()
}