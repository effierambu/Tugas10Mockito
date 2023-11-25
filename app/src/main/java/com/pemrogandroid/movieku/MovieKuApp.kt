package com.pemrogandroid.movieku

import android.app.Application

//singleton Application context
class MovieKuApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MovieKuApp
            private set
    }
}