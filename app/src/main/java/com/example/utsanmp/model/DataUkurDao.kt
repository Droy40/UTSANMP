package com.example.utsanmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataUkurDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dataukur:DataUkur)

    @Query("SELECT * FROM dataukur")
    fun selectAllDataUkur(): List<DataUkur>
}