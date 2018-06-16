package com.jirawat.bddexample.clean

import android.os.Bundle
import com.jirawat.bddexample.R
import com.jirawat.bddexample.baseclass.BaseActivity


interface IMainActivity {
    fun setView()
}

class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) : BaseActivity(),IMainActivity {
    var interactor:Iinterractor? = null
    var rounter:Rounter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        var presenter = Presenter()
        var interactor = Interactor()
        var rounter = Rounter()

        this.rounter = rounter
        rounter.mainActivity = this
        rounter.dataGoToinfo = interactor
        this.interactor = interactor
        presenter.mainActivity = this
        interactor.ipresenter = presenter
    }


    fun callAPi(){
        interactor?.callApi1()
    }

    override fun setView() {

    }

}
