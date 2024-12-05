package com.papb.brawithu.feature.splash

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.papb.brawithu.data.Repository
import com.papb.brawithu.model.user.UserModel

class SplashViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)

    init {
        if (auth.uid != null) {
            repository.getUser(
                auth.uid ?: "",
                onSuccess = {
                    Log.e("Berhasil", it.toString())
                    user.value = it
                },
                onFailed = {
                    Log.e("Gagal", it.toString())
                }
            )
        }

    }
}