package com.irza.brawithu.feature.konseling

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.irza.brawithu.data.Repository

class KonselingViewModel : ViewModel() {
    val repository = Repository()
    val isSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    val errMsg = mutableStateOf("")


    suspend fun postKonseling(
        nama: String,
        nim: String,
        jenis_kelamin: String,
        jam: String,
        fakultas: String,
    ){
        isLoading.value = true
        isSuccess.value = false
        repository.postKonseling(
            nama = nama,
            nim = nim,
            jenis_kelamin = jenis_kelamin,
            jam = jam,
            fakultas = fakultas,
            onSuccess = {
                isLoading.value = false
                isSuccess.value = true
                errMsg.value = "Berhasil Mengajukan Konseling"
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = "Gagal Mengajukan Konseling"
                Log.e("ERROR", it.toString())
            }
        )
    }
}