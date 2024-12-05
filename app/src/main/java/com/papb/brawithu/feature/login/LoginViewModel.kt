package com.papb.brawithu.feature.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.papb.brawithu.data.Repository

class LoginViewModel: ViewModel() {
    val repository = Repository()
    val isLogin = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val role = mutableStateOf("")

    val errMsg = mutableStateOf("")

    fun signIn(
        email : String,
        password : String,
    ){
        isLoading.value = true
        repository.login(
            email,
            password,
            onSuccess = {
                isLoading.value = false
                isLogin.value = true
                role.value = it
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = it.toString()
            }
        )
    }
}