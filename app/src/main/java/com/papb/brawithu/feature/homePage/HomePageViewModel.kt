package com.papb.brawithu.feature.homePage

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.papb.brawithu.data.Repository
import com.papb.brawithu.model.berita.BeritaModel
import com.papb.brawithu.model.lapor.LaporModel
import com.papb.brawithu.model.user.UserModel

class HomePageViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)
    val lapor = mutableStateListOf<LaporModel>()
    val berita = mutableStateListOf<BeritaModel>()


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

        repository.getAllLaporByUserId(
            onSuccess = {
                Log.e("Berhasil", it.toString())
                lapor.clear()
                lapor.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )

        repository.getAllBerita(
            onSuccess = {
                Log.e("Berhasil", it.toString())
                berita.clear()
                berita.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}