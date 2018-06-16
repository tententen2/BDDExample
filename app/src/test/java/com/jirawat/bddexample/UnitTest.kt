package com.jirawat.bddexample

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModel.State
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModelImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UnitTest {
    private val state: MediatorLiveData<State> = MediatorLiveData()
    private val fetchMemesUseCaseLiveData: MutableLiveData<FetchMemesUseCase.Result> = MutableLiveData()
    @Mock private lateinit var observer: Observer<State>

    @Mock
    lateinit var fetchMemesUseCase:FetchMemesUseCase
    lateinit var viewmodel:ListViewModelImpl

    @Before
    fun setup(){
        setUpLiveData()
        viewmodel = ListViewModelImpl(state,fetchMemesUseCase)
    }

    private fun setUpLiveData() {
        BDDMockito.given(fetchMemesUseCase.getLiveData()).willReturn(fetchMemesUseCaseLiveData)
        state.observeForever(observer)
    }

    @Test
    fun `fetch execute`(){
        viewmodel.fetchMemes()
        thenObserverShouldReceiveCorrectStates(State.ShowLoading)
        thenUseCaseShouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: State) {
        expected.forEach { BDDMockito.then(observer).should().onChanged(it) }
        BDDMockito.then(observer).shouldHaveNoMoreInteractions()
    }

    private fun thenUseCaseShouldHaveNoMoreInteractions() {
        then(fetchMemesUseCase).should().getLiveData()
    }

    private fun thenUseCaseExcuted(){
        then(fetchMemesUseCase).should().execute(any(), any(), any())
    }

}
