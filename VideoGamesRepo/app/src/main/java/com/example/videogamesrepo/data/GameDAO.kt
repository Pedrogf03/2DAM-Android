package com.example.videogamesrepo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: Game)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(game: Game)

    @Delete
    suspend fun delete(game: Game)

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGame(id: Int): Flow<Game>

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE titulo = :title")
    fun getGamesFromTitle(title: String): Flow<List<Game>>

}