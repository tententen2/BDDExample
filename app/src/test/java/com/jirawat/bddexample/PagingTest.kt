package com.jirawat.bddexample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import com.jirawat.bddexample.data.MainActivity.Result
import com.jirawat.bddexample.mock.FakeApi
import com.jirawat.bddexample.presentation.login.TestActivity2
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCaseImpl
import com.jirawat.bddexample.presentation.login.repository.Listing
import com.jirawat.bddexample.presentation.login.repository.PagingRepository
import com.jirawat.bddexample.presentation.login.state.ErrorTextState
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModel
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModelImpl
import com.jirawat.bddexample.presentation.login.viewslice.ListViewSliceImpl
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.Robolectric


@RunWith(MockitoJUnitRunner::class)
class PagingTest {

    @get:Rule // used to make all live data calls sync
    val instantExecutor = InstantTaskExecutorRule()
    private val fakeApi = FakeApi()
    private val repo = PagingRepository(fakeApi)

    private val state:MediatorLiveData<ListViewModel.State> = MediatorLiveData()
    private var fetchMemesUseCase: FetchMemesUseCase = FetchMemesUseCaseImpl()
    private val viewModel = ListViewModelImpl(state,fetchMemesUseCase,repo)



    @Before
    fun init(){


        var input = viewModel.getInput()
        input.username = ""
        input.password = ""
        input.phoneNumber = ""
    }

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
    fun checkinput(){
        val observer = Mockito.mock(Observer::class.java) as Observer<ErrorTextState>
        viewModel.editTextState.observeForever(observer)
        viewModel.doCheckInput()
        Mockito.verify(observer).onChanged(ErrorTextState.UsernameError("พัง"))
        Mockito.verify(observer).onChanged(ErrorTextState.PasswordError)
        Mockito.verify(observer).onChanged(ErrorTextState.PhoneError)
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