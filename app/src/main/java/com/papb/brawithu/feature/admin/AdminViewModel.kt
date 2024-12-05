package com.papb.brawithu.feature.admin

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.papb.brawithu.data.Repository
import com.papb.brawithu.model.konseling.KonselingModel
import com.papb.brawithu.model.lapor.LaporModel
import com.papb.brawithu.model.sos.SOSModel
import com.papb.brawithu.model.user.UserModel

class AdminViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val lapor = mutableStateListOf<LaporModel>()
    val konseling = mutableStateListOf<KonselingModel>()
    val sos = mutableStateListOf<SOSModel>()
    val user = mutableStateOf<UserModel?>(null)
    val isSuccess = mutableStateOf(false)


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

    init {
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

        repository.getAllLapor(
            onSuccess = {
                Log.e("Berhasil", it.toString())
                lapor.clear()
                lapor.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )

        repository.getAllKonseling(
            onSuccess = {
                Log.e("Berhasil", it.toString())
                konseling.clear()
                konseling.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )

        repository.getAllSOS(
            onSuccess = {
                Log.e("Berhasil", it.toString())
                sos.clear()
                sos.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}