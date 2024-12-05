package com.papb.brawithu.feature.lapor

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.papb.brawithu.data.Repository

class LaporViewModel: ViewModel() {
    val repository = Repository()
    val isSuccess = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    val errMsg = mutableStateOf("")


    suspend fun postLapor(
        nama: String,
        nim: String,
        jenis_kelamin: String,
        detail: String,
        lokasi: String,
    ){
        isLoading.value = true
        isSuccess.value = false
        repository.postLapor(
            nama = nama,
            nim = nim,
            jenis_kelamin = jenis_kelamin,
            detail = detail,
            lokasi = lokasi,
            onSuccess = {
                isLoading.value = false
                isSuccess.value = true
                errMsg.value = "Berhasil Mengirim Laporan"
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = "Gagal Mengirim Laporan"
                Log.e("ERROR", it.toString())
            }
        )
    }
}