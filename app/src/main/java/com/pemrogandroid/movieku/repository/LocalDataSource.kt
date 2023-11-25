package com.pemrogandroid.movieku.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.pemrogandroid.movieku.db.LocalDatabase
import com.pemrogandroid.movieku.db.MovieDao
import com.pemrogandroid.movieku.model.Movie
import kotlin.concurrent.thread

open class LocalDataSource(application: Application) {

    private val movieDao: MovieDao

    init {
        val db = LocalDatabase.getInstance(application)
        movieDao = db.movieDao()
    }

    val allMovies: LiveData<List<Movie>>
        get() {
            return movieDao.loadAll()
        }

    fun insert(movie: Movie) {
        thread {
            movieDao.insert(movie)
        }
    }

    fun delete(movie: Movie) {
        thread {
            movieDao.delete(movie.id)
        }
    }

    fun update(movie: Movie) {
        thread {
            movieDao.update(movie)
        }
    }

    fun getMovie(id: Int): Movie {
        return movieDao.getMovie(id)
    }

}