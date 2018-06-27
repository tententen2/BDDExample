package com.jirawat.bddexample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModelProviders
import android.widget.Button
import android.widget.EditText
import com.jirawat.bddexample.mock.FakeApi
import com.jirawat.bddexample.presentation.login.TestActivity
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCaseImpl
import com.jirawat.bddexample.presentation.login.repository.PagingRepository
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModel
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModelFactory
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [26])
class Robolec {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var editUsername: EditText
    private lateinit var editPhone: EditText
    private lateinit var buttonOk:Button

    private val fakeApi = FakeApi()
    private val repo = PagingRepository(fakeApi)

    @Before
    fun setUp(){
        var activityController = Robolectric.setupActivity(TestActivity::class.java)
        activityController.livemodel = ViewModelProviders.of(activityController, ListViewModelFactory(FetchMemesUseCaseImpl(),repo)).get(ListViewModel::class.java)
        editUsername = activityController.findViewById(R.id.usernameInput)
        editPhone = activityController.findViewById(R.id.phoneInput)
        buttonOk = activityController.findViewById(R.id.buttonOk)


    }

    @Test
    fun editUsername_Empty_Show(){
        editUsername.setText("")
        buttonOk.performClick()
        assertThat(editUsername.error.toString(),`is`("พัง"))
        assertThat(editPhone.error.toString(),`is`("error"))
    }


}