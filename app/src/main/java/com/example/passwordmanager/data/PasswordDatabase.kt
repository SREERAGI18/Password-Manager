package com.example.passwordmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passwordmanager.data.dao.PasswordDao
import com.example.passwordmanager.data.model.PasswordEntry

@Database(entities = [PasswordEntry::class], version = 1, exportSchema = false)
abstract class PasswordDatabase : RoomDatabase() {

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PasswordDatabase? = null

        fun getDatabase(context: Context): PasswordDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            return INSTANCE ?: synchronized(this) {
                val instanceBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    PasswordDatabase::class.java,
                    "password.db"
                )
                val instance = instanceBuilder.build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    abstract fun passwordDao(): PasswordDao
}