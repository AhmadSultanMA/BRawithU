package com.irza.brawithu.feature.sos

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.brawithu.data.Repository
import com.irza.brawithu.model.user.UserModel

class SOSViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)


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
    }

    suspend fun postSOS(
        nama: String,
        fakultas: String,
    ){
        repository.postSOS(
            nama = nama,
            fakultas = fakultas,
            onSuccess = {
                Log.e("Berhasil", "Berhasil Mengajukan SOS")
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }
}