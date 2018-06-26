package com.jirawat.bddexample.presentation.login.repository

import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.data.MainActivity.Result

interface TestRepository {
    fun postsOfList(pageSize:Int): Listing<Result>
}