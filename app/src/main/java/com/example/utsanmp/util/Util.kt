package com.example.utsanmp.util

import android.content.Context
import com.example.utsanmp.model.UkurDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context): UkurDatabase {
    val db = UkurDatabase.buildDatabase(context)
    return db
}