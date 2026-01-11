package com.example.utsanmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataUkur (
    @ColumnInfo(name="beratBadan")
    var beratBadan: Int?,

    @ColumnInfo(name="tinggiBadan")
    var tinggiBadan: Int?,

    @ColumnInfo(name="usia")
    var usia: Int?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0

    var beratBadanStr: String
        get() = beratBadan?.toString() ?: ""
        set(value) {
            beratBadan = value.toIntOrNull()
        }

    var tinggiBadanStr: String
        get() = tinggiBadan?.toString() ?: ""
        set(value) {
            tinggiBadan = value.toIntOrNull()
        }

    var usiaStr: String
        get() = usia?.toString() ?: ""
        set(value) {
            usia = value.toIntOrNull()
        }
}
