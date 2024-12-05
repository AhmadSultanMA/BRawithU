package com.papb.brawithu.model.konseling

data class KonselingModel(
    val id: String,
    val user_id: String,
    val jam: String,
    val fakultas: String,
    val nama: String,
    val nim: String,
    val jenis_kelamin: String,
    val status: String
)