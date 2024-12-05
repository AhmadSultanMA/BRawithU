package com.papb.brawithu.feature.artikelDetail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.papb.brawithu.data.Repository
import com.papb.brawithu.model.berita.BeritaModel

class ArtikelDetailViewModel: ViewModel() {
    val repository = Repository()
    val berita = mutableStateOf<BeritaModel?>(null)

    fun getBeritaById(
        id : String
    ){
        repository.getBeritaById(
            id ,
            onSuccess = {
                berita.value = it
            },
            onFailed = {
                Log.e("ERROR", it.toString())
            }
        )
    }
}