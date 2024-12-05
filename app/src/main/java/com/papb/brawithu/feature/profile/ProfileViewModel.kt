package com.papb.brawithu.feature.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.papb.brawithu.data.Repository
import com.papb.brawithu.model.user.UserModel

class ProfileViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val isSuccess = mutableStateOf(false)
    val user = mutableStateOf<UserModel?>(null)

    init {
        repository.getUser(
            auth.uid ?: "",
            onSuccess = {
                user.value = it
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }

    fun logout() {
        repository.logout(
            onSuccess = {
                isSuccess.value = true
                Log.e("Berhasil", "Logout Berhasil")
            },
            onFailed = {
                isSuccess.value = false
                Log.e("Gagal", it.toString())
            }
        )
    }

}