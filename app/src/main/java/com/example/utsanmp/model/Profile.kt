package com.example.utsanmp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (
    var nama: String?,
    var tanggalLahir: String?,
    var jenisKelamin: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}