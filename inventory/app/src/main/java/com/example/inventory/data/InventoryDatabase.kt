package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase(){

    abstract fun itemDAO(): ItemDAO

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context) : InventoryDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }

    }

}