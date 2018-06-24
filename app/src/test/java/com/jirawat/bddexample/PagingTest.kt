package com.jirawat.bddexample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.util.Log
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.mock.FakeApi
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.repository.Listing
import com.jirawat.bddexample.presentation.login.repository.PagingRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.verification.VerificationMode
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class PagingTest {

    @get:Rule // used to make all live data calls sync
    val instantExecutor = InstantTaskExecutorRule()
    private val fakeApi = FakeApi()
    private val repo = PagingRepository(fakeApi)


    @Test
    fun emptyList() {
        val listing = repo.postsOfList(10)
        val pagedList = getPageList(listing)
        assertThat(pagedList.size,`is`(0))
    }

    @Test
    fun oneItem(){
        val result = Result(voteAverage = 20.2)
        fakeApi.addPost(result)
        val listing = repo.postsOfList(10)
        assertThat(getPageList(listing), `is`(listOf(result)))
    }

    @Test
    fun twoItem(){
        val result = Result(voteAverage = 20.2)
        (0..1).map { result }.forEach(fakeApi::addPost)
        val listing = repo.postsOfList(10)
        val paging = getPageList(listing)
        assertThat(paging.size, `is`(2))
    }

    @Test
    fun verifyCompleteList(){
        val result = Result(adult = true)
        (0..10).map { result }.forEach(fakeApi::addPost)
        val listing = repo.postsOfList(11)
        val paging = getPageList(listing)
        paging.loadAround(paging.size)
        assertThat(paging.size,`is`(22))
    }

    @Test
    fun failToLoad(){

    }



    private fun getPageList(listing:Listing<Result>):PagedList<Result>{
        val observer = LoggingObserver<PagedList<Result>>()
        listing.pagedList.observeForever(observer)
        assertThat(observer.value, `is`(notNullValue()))
        return observer.value!!
    }

    private class LoggingObserver<T> : Observer<T> {
        var value : T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }




}