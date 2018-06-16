package com.jirawat.bddexample.clean


interface RountingLogic{
    fun goToinfo()
}
interface PassData{
    var dataGoToinfo:rowData
}

class Rounter :RountingLogic,PassData{
    override lateinit var dataGoToinfo: rowData

    override fun goToinfo() {

    }

    var mainActivity:IMainActivity? = null
    var interactor:Iinterractor? = null

    sealed class Page{
        object List :Page()
        object Detail:Page()
    }
}