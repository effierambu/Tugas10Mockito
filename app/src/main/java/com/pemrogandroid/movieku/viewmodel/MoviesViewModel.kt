package com.pemrogandroid.movieku.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.LocalDataSource

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "MoviesViewModel"

    private var localDataSource: LocalDataSource = LocalDataSource(getApplication())

    private var movies: LiveData<List<Movie>>? = null

    fun addMovie(movie: Movie) {
        localDataSource.insert(movie)

        Log.i(TAG, "New movie added to the database.")
    }

    fun getMovies():
            LiveData<List<Movie>>? {

        if (movies == null) {
            movies = localDataSource.allMovies
        }

        return movies
    }

    fun getMovie(id: Int): Movie {
        val movie = localDataSource.getMovie(id)
        return movie;
    }

    fun updateDetails(movie: Movie) {
        localDataSource.update(movie)

        Log.i(TAG, "movie updated on the database.")
    }

    fun delete(movie: Movie) {
        localDataSource.delete(movie)
    }

}