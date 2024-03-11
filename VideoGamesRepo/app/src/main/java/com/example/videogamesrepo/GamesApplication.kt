package com.example.videogamesrepo

import android.app.Application
import com.example.videogamesrepo.data.AppContainer
import com.example.videogamesrepo.data.DefaultAppContainer

class GamesApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

}