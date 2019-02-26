package com.jirawat.bddexample

import com.jirawat.bddexample.firestpage.PullToRefresh
import com.jirawat.bddexample.secpage.SecTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(SecTest::class,PullToRefresh::class)
class TestSuiteExample
