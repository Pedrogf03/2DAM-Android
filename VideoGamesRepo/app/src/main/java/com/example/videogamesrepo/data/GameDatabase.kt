package com.example.videogamesrepo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao() : GameDAO

    companion object {
        @Volatile
        private var Instance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GameDatabase::class.java, "game_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}