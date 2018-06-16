package com.jirawat.bddexample.clean

interface IPresenter{
    fun presentFromApi()
}
class Presenter:IPresenter {
    var mainActivity:IMainActivity? = null

    override fun presentFromApi() {
        mainActivity?.setView()
    }
}