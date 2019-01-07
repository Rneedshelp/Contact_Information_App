package com.example.everj.a411_application_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

//CREATION OF THE DATABASE WHEN AN INSTANCE BEGINS AND NO OTHER DATABASES ARE LIVE
//PREVENTS MULTIPLE COPIES OF THE SAME DATABASE EXISTING
@Database(entities = [Contact::class], version = 3)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun wordDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}