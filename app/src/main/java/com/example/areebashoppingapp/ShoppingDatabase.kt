package com.example.areebashoppingapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [ShoppingItem ::class], version = 2)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getItemsDao() : ShoppingDao

    companion object
    {
        @Volatile
        private var instance :ShoppingDatabase? =null
        private val LOCK =Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK)
        {
            instance ?:createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context :Context)=
            Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java,
"Shopping.db").fallbackToDestructiveMigration().build()

    }
}