package com.jirawat.bddexample.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.jirawat.bddexample.baseclass.BaseActivity
import com.jirawat.bddexample.R
import com.jirawat.bddexample.data.MainActivity.Result
import kotlinx.android.synthetic.main.activity_sec.*


class SecActivity(override val layoutResourceId: Int = R.layout.activity_sec) : BaseActivity() {
    lateinit var data: Result

    companion object {
        private val DATA = "extra_data_sec"
        fun start(context: Context,data:Result):Intent {
            var intent = Intent(context,SecActivity::class.java)
            intent.putExtra(DATA,data)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableExtra(DATA)
        title_sec.text = data.title
        detail_sec.text = data.overview

    }
}
