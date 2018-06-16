package com.jirawat.bddexample.clean

interface Iinterractor {
    fun callApi1()
    fun callApi2()
}

interface rowData{
    var sasassa:String
}

class Interactor :Iinterractor,rowData {

    override var sasassa: String = ""

    var ipresenter:IPresenter? = null
    var worker:worker = worker()

    override fun callApi1() {
        //success
        ipresenter?.presentFromApi()

        sasassa =  "success"

        worker.getlist()
    }

    override fun callApi2() {
    }
}