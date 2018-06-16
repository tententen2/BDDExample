package com.jirawat.bddexample

import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import com.jirawat.bddexample.data.MainActivity.ResponseMainActivity
import com.jirawat.bddexample.presentation.login.domain.FetchMemesUseCase
import com.jirawat.bddexample.presentation.login.repository.MainRepository
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModel
import com.jirawat.bddexample.presentation.login.viewmodel.ListViewModelImpl
import org.hamcrest.CoreMatchers.describedAs
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var contextTest: Context = mock(Context::class.java)

    var FAKE_STRING: String = "ExampleUnitTest2"

    lateinit var worker: MainRepository

    lateinit var interact: FetchMemesUseCase

    @Before
    fun testBefore2() {
        FAKE_STRING = "ExampleUnitTest2"
        worker = mock(MainRepository::class.java)
        interact = mock(FetchMemesUseCase::class.java)
    }

//
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }
//
//    @Test
//    fun test1(){
//        Assert.assertTrue("test",true)
//    }
//
//    @Test
//    fun test2(){
//        Assert.assertFalse("false",false)
//    }
//
//    @Test
//    fun test3(){
//        Assert.assertTrue("test",false)
//        Assert.assertTrue("test2",false)
////        assertEquals()
////        assertThat("tasty", both(containsString("as")).and())
////        assertThat("tasty", "sa", allOf(containsString("s"),containsString("sa11")))
//    }
//
//
//    @Test
//    fun test4(){
//        describedAs("a big decimal equal to %0", equalTo(11),10.1)
//    }


//    @Test
//    fun test5(){
//        `when`(contextTest.getString(R.string.app_name))
//                .thenReturn(FAKE_STRING)
//
//        assertEquals(UniTest(contextTest).getAppName(),FAKE_STRING)

//    }
}

fun <T> any(): T = Mockito.any<T>()


//class UniTest(val context:Context){
//
//    fun getAppName():String = context.getString(R.string.app_name)
//}

