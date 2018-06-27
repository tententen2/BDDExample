package com.jirawat.bddexample.presentation.login.viewslice

import com.jirawat.bddexample.baseclass.viewslice.BaseViewSlice
import com.jirawat.bddexample.baseclass.viewslice.StateViewSlice
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


private const val STATE_CONTENT = 0
private const val STATE_LOADING = 1
private const val STATE_ERROR = 2

class StateViewSliceImpl @Inject constructor(
) : BaseViewSlice(),
        StateViewSlice {

    override fun showLoading() {
        showState(STATE_LOADING)
    }

    override fun showContent() {
        showState(STATE_LOADING)
    }

    override fun showError() {
        showState(STATE_LOADING)
    }

    private fun showState(state: Int) {
        if (list_state_view_flipper.displayedChild != state) {
            list_state_view_flipper.displayedChild = state
        }
    }
}
