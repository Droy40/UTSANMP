package com.example.utsanmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.utsanmp.util.DB_NAME

@Database(entities = [Profile::class, DataUkur::class], version =  1)
abstract class UkurDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun dataUkurDao(): DataUkurDao

    companion object {
        @Volatile private var instance: UkurDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,
            UkurDatabase::class.java, DB_NAME)
//            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
        operator fun invoke(context:Context) {
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }

}
