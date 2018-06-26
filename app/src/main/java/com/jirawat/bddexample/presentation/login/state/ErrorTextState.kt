package com.jirawat.bddexample.presentation.login.state

sealed class ErrorTextState {
    data class UsernameError(var message:String) : ErrorTextState()
    object PasswordError : ErrorTextState()
    object PhoneError : ErrorTextState()
}