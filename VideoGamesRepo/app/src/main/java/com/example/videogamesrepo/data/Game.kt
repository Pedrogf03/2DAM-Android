package com.example.videogamesrepo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat

@Entity(tableName = "Games")
@Serializable
class Game (

    @PrimaryKey
    @SerialName(value="id")
    val id: Int,
    @SerialName(value="title")
    val titulo: String,
    @SerialName(value="thumbnail")
    val img: String,
    @SerialName(value="genre")
    val genero: String,
    @SerialName(value="platform")
    val plataforma: String,
    @SerialName(value="developer")
    val desarrollador: String,
    @SerialName(value="release_date")
    val fecha: String,
    @SerialName(value="short_description")
    val desc: String

) {

    fun formatDate(): String{

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = dateFormat.parse(fecha)

        val newFormat = SimpleDateFormat("dd/MM/yyyy")
        return newFormat.format(parsedDate!!)

    }

}