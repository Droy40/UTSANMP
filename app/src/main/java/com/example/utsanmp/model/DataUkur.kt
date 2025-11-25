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
    @PrimaryKey(autoGenerate = true) //Each entity class requires one primary key. Autogenerate (Auto increment (?) ) config set to true to let SQLite generate the unique id
    var uuid:Int =0

}
