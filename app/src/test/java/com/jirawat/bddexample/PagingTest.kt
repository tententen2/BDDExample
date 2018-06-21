package com.jirawat.bddexample

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.mock.FakeApi
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.repository.Listing
import com.jirawat.bddexample.presentation.login.repository.PagingRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
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
    private val fakeApi = FakeApi()
    private val repo = PagingRepository(fakeApi)


    @Test
    fun emptyList() {
        val listing = repo.postsOfList()
        val pagedList = getPageList(listing)
        assertThat(pagedList.size,`is`(0))
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