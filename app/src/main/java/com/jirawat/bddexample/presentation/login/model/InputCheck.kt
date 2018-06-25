package com.jirawat.bddexample.presentation.login.model

class InputCheck {
    var username: String = ""
    var password: String = ""
    var phoneNumber: String = ""

    fun checkValidUsername():Boolean = username.isNotEmpty()

    fun checkValidPassword():Boolean = password.isNotEmpty()

    fun checkValidPhoneNumber(): Boolean = phoneNumber.isNotEmpty()
}