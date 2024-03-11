package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val nombre: Int,
    val numCursos: Int,
    @DrawableRes val image: Int
)