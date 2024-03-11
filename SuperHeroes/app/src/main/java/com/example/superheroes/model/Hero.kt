package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.superheroes.R

data class Hero(
    @DrawableRes val imageId: Int,
    @StringRes val nombre: Int,
    @StringRes val desc: Int
)

val heroes = listOf(
    Hero(
        imageId = R.drawable.android_superhero1,
        nombre = R.string.hero1,
        desc = R.string.description1
    ),
    Hero(
        imageId = R.drawable.android_superhero2,
        nombre = R.string.hero2,
        desc = R.string.description2
    ),
    Hero(
        imageId = R.drawable.android_superhero3,
        nombre = R.string.hero3,
        desc = R.string.description3
    ),
    Hero(
        imageId = R.drawable.android_superhero4,
        nombre = R.string.hero4,
        desc = R.string.description4
    ),
    Hero(
        imageId = R.drawable.android_superhero5,
        nombre = R.string.hero5,
        desc = R.string.description5
    ),
    Hero(
        imageId = R.drawable.android_superhero6,
        nombre = R.string.hero6,
        desc = R.string.description6
    )
)
