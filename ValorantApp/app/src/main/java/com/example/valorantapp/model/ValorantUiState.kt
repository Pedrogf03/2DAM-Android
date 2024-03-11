package com.example.valorantapp.model

import androidx.annotation.DrawableRes
import com.example.valorantapp.R


data class ValorantUiState(
    val agentes: List<Agente>? = null,
    val agente: Agente? = null,
    val armas: List<Arma>? = null,
    val arma: Arma? = null,
    @DrawableRes val image: Int = R.drawable.background
)
