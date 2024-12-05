package com.papb.brawithu.feature.signUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.papb.brawithu.data.Repository

class SignUpViewModel: ViewModel() {
    private val repository = Repository()

    val isLoading = mutableStateOf(false)
    val isSuccess = mutableStateOf(false)

    val errMsg = mutableStateOf("")

    fun signUp(
        email : String,
        password : String,
        nama : String,
    ){
        isLoading.value = true
        isSuccess.value = false
        repository.signUp(
            email,
            password,
            nama,
            onSuccess = {
                Log.e("Berhasil", "Sign Up Berhasil")
                isSuccess.value = true
                isLoading.value = false
            },
            onFailed = {
                isLoading.value = false
                isSuccess.value = false
                errMsg.value = it.toString()
            }
        )
    }
}