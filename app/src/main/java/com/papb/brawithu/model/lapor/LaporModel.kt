package com.papb.brawithu.model.lapor

data class LaporModel (
    val id: String,
    val user_id: String,
    val lokasi: String,
    val detail: String,
    val nama: String,
    val nim: String,
    val jenis_kelamin: String,
    val status: String
)